package com.JPA.SistemaHospitalar.dto.medico;

import jakarta.validation.constraints.NotBlank;

public record MedicoRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Especialidade é obrigatória")
        String especialidade,

        @NotBlank(message = "CRM é obrigatório")
        String crm
) {}
