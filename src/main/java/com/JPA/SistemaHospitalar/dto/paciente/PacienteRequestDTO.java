package com.JPA.SistemaHospitalar.dto.paciente;

import jakarta.validation.constraints.NotBlank;

public record PacienteRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone
) {}
