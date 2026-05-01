package com.JPA.SistemaHospitalar.dto.consulta;

import com.JPA.SistemaHospitalar.dto.receita.ReceitaRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(
        @NotNull(message = "Data e hora são obrigatórias")
        LocalDateTime dataHora,

        @NotBlank(message = "Motivo é obrigatório")
        String motivo,

        @Positive(message = "Valor deve ser positivo")
        double valor,

        @NotNull(message = "ID do paciente é obrigatório")
        Long pacienteId,

        @NotNull(message = "ID do médico é obrigatório")
        Long medicoId,

        @NotNull(message = "ID do convênio é obrigatório")
        Long convenioId,

        ReceitaRequestDTO receita
) {}
