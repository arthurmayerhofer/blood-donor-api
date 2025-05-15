package com.blooddonor.blood_donor_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "donors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String rg;

    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    private String sexo;
    private String mae;
    private String pai;

    @Column(unique = true)
    private String email;

    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;

    @Column(name = "telefone_fixo")
    private String telefoneFixo;

    private String celular;
    private Double altura;
    private Double peso;

    @Column(name = "tipo_sanguineo")
    private String tipoSanguineo;

    // Método derivado (não persistido)
    @Transient
    public Double getImc() {
        return peso / (altura * altura);
    }

    public Double getHeight() {
        return altura;
    }

    public Double getWeight() {
        return peso;
    }

    public Integer getAge() {
        return dataNascimento != null ? LocalDate.now().getYear() - dataNascimento.getYear() : null;
    }

    public String getGender() {
        return sexo;
    }

    public String getState() {
        return estado;
    }

    public String getBloodType() {
        return tipoSanguineo;
    }
}
