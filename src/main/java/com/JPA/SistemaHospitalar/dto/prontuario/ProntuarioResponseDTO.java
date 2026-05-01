package com.JPA.SistemaHospitalar.dto.prontuario;

public record ProntuarioResponseDTO(
        Long id,
        String tipoSanguineo,
        String alergia,
        String observacoes
) {}
