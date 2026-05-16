package com.JPA.SistemaHospitalar.mapper;

import com.JPA.SistemaHospitalar.entity.Prontuario;
import com.JPA.SistemaHospitalar.dto.prontuario.ProntuarioRequestDTO;
import com.JPA.SistemaHospitalar.dto.prontuario.ProntuarioResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProntuarioMapper {

    public Prontuario toEntity(ProntuarioRequestDTO dto) {
        return new Prontuario(dto.tipoSanguineo(), dto.alergia(), dto.observacoes());
    }

    public ProntuarioResponseDTO toResponseDTO(Prontuario prontuario) {
        return new ProntuarioResponseDTO(
                prontuario.getId(),
                prontuario.getTipoSanguineo(),
                prontuario.getAlergia(),
                prontuario.getObservacoes()
        );
    }
}
