package com.JPA.SistemaHospitalar.mapper;

import com.JPA.SistemaHospitalar.entity.Paciente;
import com.JPA.SistemaHospitalar.dto.paciente.PacienteRequestDTO;
import com.JPA.SistemaHospitalar.dto.paciente.PacienteResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    private final ProntuarioMapper prontuarioMapper;

    public PacienteMapper(ProntuarioMapper prontuarioMapper) {
        this.prontuarioMapper = prontuarioMapper;
    }

    public Paciente toEntity(PacienteRequestDTO dto) {
        return new Paciente(dto.nome(), dto.cpf(), dto.telefone());
    }

    public PacienteResponseDTO toResponseDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getProntuario() != null
                        ? prontuarioMapper.toResponseDTO(paciente.getProntuario())
                        : null
        );
    }
}
