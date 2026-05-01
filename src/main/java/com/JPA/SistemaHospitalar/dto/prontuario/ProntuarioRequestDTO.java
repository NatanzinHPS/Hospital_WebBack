package com.JPA.SistemaHospitalar.dto.prontuario;

import jakarta.validation.constraints.NotBlank;

public record ProntuarioRequestDTO(
        @NotBlank(message = "Tipo sanguíneo é obrigatório")
        String tipoSanguineo,

        String alergia,

        String observacoes
) {}
