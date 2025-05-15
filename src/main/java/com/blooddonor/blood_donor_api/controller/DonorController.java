package com.blooddonor.blood_donor_api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blooddonor.blood_donor_api.dto.DonorDTO;
import com.blooddonor.blood_donor_api.service.DonorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/donors")
@RequiredArgsConstructor
@Validated
public class DonorController {

    private final DonorService donorService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping
    public ResponseEntity<List<DonorDTO>> getAllDonors() {
        // Implementação para listar todos os doadores
        return ResponseEntity.ok(donorService.getAllDonors());
    }

    @PostMapping("/donors")
    public ResponseEntity<Void> addDonor(@Valid @RequestBody DonorDTO donorDTO) {
        donorService.addDonor(donorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/batch")
    public ResponseEntity<Void> addDonorsBatch(@Valid @RequestBody List<DonorDTO> donorDTOs) {
        donorService.saveAllDonors(donorDTOs);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/count-by-state")
    public ResponseEntity<Map<String, Long>> countCandidatesByState() {
        return ResponseEntity.ok(donorService.countCandidatesByState());
    }

    @GetMapping("/average-bmi-by-age-range")
    public ResponseEntity<Map<String, Double>> calculateAverageBMIByAgeRange() {
        return ResponseEntity.ok(donorService.calculateAverageBMIByAgeRange());
    }

    @GetMapping("/obesity-percentage-by-gender")
    public ResponseEntity<Map<String, Double>> calculateObesityPercentageByGender() {
        return ResponseEntity.ok(donorService.calculateObesityPercentageByGender());
    }

    @GetMapping("/average-age-by-blood-type")
    public ResponseEntity<Map<String, Double>> calculateAverageAgeByBloodType() {
        return ResponseEntity.ok(donorService.calculateAverageAgeByBloodType());
    }

    @GetMapping("/potential-donors-by-recipient")
    public ResponseEntity<Map<String, Long>> calculatePotentialDonorsByRecipientType() {
        return ResponseEntity.ok(donorService.calculatePotentialDonorsByRecipientType());
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllDonors() {
        donorService.deleteAllDonors();
        return ResponseEntity.noContent().build();
    }
}
