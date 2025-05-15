package com.blooddonor.blood_donor_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blooddonor.blood_donor_api.model.Donor;

import java.util.List;
import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {

    @Query("SELECT d.estado, COUNT(d) FROM Donor d GROUP BY d.estado")
    List<Object[]> countDonorsByEstado();

    Optional<Donor> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
