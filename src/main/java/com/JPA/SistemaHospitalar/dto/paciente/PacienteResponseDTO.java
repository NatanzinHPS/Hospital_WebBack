package com.JPA.SistemaHospitalar.dto.paciente;

import com.JPA.SistemaHospitalar.dto.prontuario.ProntuarioResponseDTO;

public record PacienteResponseDTO(
        Long id,
        String nome,
        String cpf,
        String telefone,
        ProntuarioResponseDTO prontuario
) {}
