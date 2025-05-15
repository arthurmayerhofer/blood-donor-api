package com.blooddonor.blood_donor_api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.blooddonor.blood_donor_api.dto.DonorDTO;
import com.blooddonor.blood_donor_api.model.Donor;
import com.blooddonor.blood_donor_api.repository.DonorRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class DonorService {

    private final DonorRepository donorRepository;
    private final ModelMapper modelMapper;

    public void saveAllDonors(@Valid List<DonorDTO> donorDTOs) {
        List<Donor> donors = donorDTOs.stream()
                .map(dto -> {
                    log.info("Mapping DonorDTO: {}", dto);
                    Donor donor = modelMapper.map(dto, Donor.class);
                    log.info("Mapped Donor: {}", donor);
                    if (donor.getDataNascimento() == null || donor.getTipoSanguineo() == null) {
                        throw new IllegalArgumentException("Campos obrigatórios ausentes: data_nasc ou tipo_sanguineo");
                    }
                    return donor;
                })
                .collect(Collectors.toList());

        donorRepository.saveAll(donors);
    }

    public List<DonorDTO> getAllDonors() {
        return donorRepository.findAll().stream()
                .map(donor -> modelMapper.map(donor, DonorDTO.class))
                .collect(Collectors.toList());
    }

    public void addDonor(@Valid DonorDTO donorDTO) {
        Donor donor = modelMapper.map(donorDTO, Donor.class);
        donorRepository.save(donor);
    }

    public Map<String, Long> countCandidatesByState() {
        return donorRepository.findAll().stream()
                .collect(Collectors.groupingBy(Donor::getState, Collectors.counting()));
    }

    public Map<String, Double> calculateAverageBMIByAgeRange() {
        return donorRepository.findAll().stream()
                .filter(donor -> donor.getWeight() != null && donor.getHeight() != null && donor.getHeight() > 0)
                .collect(Collectors.groupingBy(
                        donor -> {
                            int age = donor.getAge();
                            return (age / 10) * 10 + "-" + ((age / 10) * 10 + 9);
                        },
                        Collectors.averagingDouble(donor -> donor.getWeight() / Math.pow(donor.getHeight(), 2))
                ))
                .entrySet().stream()
                .filter(entry -> !entry.getValue().isNaN()) // Remove groups with no valid donors
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> calculateObesityPercentageByGender() {
        List<Donor> donors = donorRepository.findAll();
        Map<String, Long> totalByGender = donors.stream()
                .collect(Collectors.groupingBy(Donor::getGender, Collectors.counting()));

        Map<String, Long> obeseByGender = donors.stream()
                .filter(donor -> donor.getWeight() / Math.pow(donor.getHeight(), 2) > 30)
                .collect(Collectors.groupingBy(Donor::getGender, Collectors.counting()));

        return obeseByGender.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> totalByGender.get(entry.getKey()) != 0
                        ? (entry.getValue() * 100.0) / totalByGender.get(entry.getKey())
                        : 0.0
                ));
    }

    public Map<String, Double> calculateAverageAgeByBloodType() {
        return donorRepository.findAll().stream()
                .filter(donor -> donor.getAge() != null && donor.getBloodType() != null)
                .collect(Collectors.groupingBy(
                        Donor::getBloodType,
                        Collectors.averagingInt(Donor::getAge)
                ))
                .entrySet().stream()
                .filter(entry -> !entry.getValue().isNaN()) // Remove groups with no valid donors
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Long> calculatePotentialDonorsByRecipientType() {
        List<Donor> donors = donorRepository.findAll();

        Map<String, List<String>> bloodTypeCompatibility = Map.of(
                "A+", List.of("A+", "AB+"),
                "A-", List.of("A+", "A-", "AB+", "AB-"),
                "B+", List.of("B+", "AB+"),
                "B-", List.of("B+", "B-", "AB+", "AB-"),
                "AB+", List.of("AB+"),
                "AB-", List.of("AB+", "AB-"),
                "O+", List.of("A+", "B+", "O+", "AB+"),
                "O-", List.of("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-")
        );

        return bloodTypeCompatibility.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> donors.stream()
                                .filter(donor -> donor.getAge() >= 16 && donor.getAge() <= 69 && donor.getWeight() > 50)
                                .filter(donor -> entry.getValue().contains(donor.getBloodType()))
                                .count()
                ));
    }

    public void deleteAllDonors() {
        donorRepository.deleteAll();
    }
}
