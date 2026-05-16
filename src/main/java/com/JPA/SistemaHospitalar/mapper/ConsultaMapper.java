package com.JPA.SistemaHospitalar.mapper;

import com.JPA.SistemaHospitalar.entity.Consulta;
import com.JPA.SistemaHospitalar.dto.consulta.ConsultaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    private final PacienteMapper pacienteMapper;
    private final MedicoMapper medicoMapper;
    private final ConvenioMapper convenioMapper;
    private final ReceitaMapper receitaMapper;

    public ConsultaMapper(PacienteMapper pacienteMapper,
                          MedicoMapper medicoMapper,
                          ConvenioMapper convenioMapper,
                          ReceitaMapper receitaMapper) {
        this.pacienteMapper = pacienteMapper;
        this.medicoMapper = medicoMapper;
        this.convenioMapper = convenioMapper;
        this.receitaMapper = receitaMapper;
    }

    public ConsultaResponseDTO toResponseDTO(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getDataHora(),
                consulta.getMotivo(),
                consulta.getValor(),
                pacienteMapper.toResponseDTO(consulta.getPaciente()),
                medicoMapper.toResponseDTO(consulta.getMedico()),
                convenioMapper.toResponseDTO(consulta.getConvenio()),
                consulta.getReceita() != null
                        ? receitaMapper.toResponseDTO(consulta.getReceita())
                        : null
        );
    }
}
