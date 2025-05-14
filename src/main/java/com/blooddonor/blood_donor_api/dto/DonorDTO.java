package com.blooddonor.blood_donor_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Data de nascimento inválida")
    private LocalDate data_nasc;

    @NotBlank(message = "Sexo é obrigatório")
    private String sexo;

    @DecimalMin(value = "0.5", message = "Altura mínima 0.5m")
    @DecimalMax(value = "2.5", message = "Altura máxima 2.5m")
    private Double altura;

    @Min(value = 1, message = "Peso mínimo 1kg")
    private Double peso;

    private String rg;
    private String mae;
    private String pai;

    @Email(message = "Email inválido")
    private String email;

    private String cep;
    private String endereco;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;

    private String telefoneFixo;
    private String celular;

    private String tipoSanguineo;
}