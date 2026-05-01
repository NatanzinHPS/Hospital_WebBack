package com.JPA.SistemaHospitalar.dto.receita;

public record ReceitaResponseDTO(
        Long id,
        String medicamento,
        String dosagem,
        int duracaoDias
) {}
