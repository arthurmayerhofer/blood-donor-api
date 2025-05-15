package com.blooddonor.blood_donor_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.blooddonor.blood_donor_api.dto.DonorDTO;
import com.blooddonor.blood_donor_api.model.Donor;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Explicit mapping for DonorDTO to Donor
        modelMapper.typeMap(DonorDTO.class, Donor.class).addMappings(mapper -> {
            mapper.map(DonorDTO::getData_nasc, Donor::setDataNascimento);
            mapper.map(DonorDTO::getTipoSanguineo, Donor::setTipoSanguineo);
        });

        // Explicit mapping for Donor to DonorDTO
        modelMapper.typeMap(Donor.class, DonorDTO.class).addMappings(mapper -> {
            mapper.map(Donor::getDataNascimento, DonorDTO::setData_nasc);
            mapper.map(Donor::getTipoSanguineo, DonorDTO::setTipoSanguineo);
        });

        return modelMapper;
    }
}
