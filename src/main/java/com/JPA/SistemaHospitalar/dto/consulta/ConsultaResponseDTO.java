package com.JPA.SistemaHospitalar.dto.consulta;

import com.JPA.SistemaHospitalar.dto.convenio.ConvenioResponseDTO;
import com.JPA.SistemaHospitalar.dto.medico.MedicoResponseDTO;
import com.JPA.SistemaHospitalar.dto.paciente.PacienteResponseDTO;
import com.JPA.SistemaHospitalar.dto.receita.ReceitaResponseDTO;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long id,
        LocalDateTime dataHora,
        String motivo,
        double valor,
        PacienteResponseDTO paciente,
        MedicoResponseDTO medico,
        ConvenioResponseDTO convenio,
        ReceitaResponseDTO receita
) {}
