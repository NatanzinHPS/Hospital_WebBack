package com.JPA.SistemaHospitalar.dto.convenio;

import jakarta.validation.constraints.NotBlank;

public record ConvenioRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CNPJ é obrigatório")
        String cnpj
) {}
