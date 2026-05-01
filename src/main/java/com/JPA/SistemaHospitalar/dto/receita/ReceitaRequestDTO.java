package com.JPA.SistemaHospitalar.dto.receita;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ReceitaRequestDTO(
        @NotBlank(message = "Medicamento é obrigatório")
        String medicamento,

        @NotBlank(message = "Dosagem é obrigatória")
        String dosagem,

        @Min(value = 1, message = "Duração deve ser de pelo menos 1 dia")
        int duracaoDias
) {}
