package com.JPA.SistemaHospitalar.dto.medico;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String especialidade,
        String crm
) {}
