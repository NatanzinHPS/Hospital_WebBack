package com.JPA.SistemaHospitalar.mapper;

import com.JPA.SistemaHospitalar.Entity.Medico;
import com.JPA.SistemaHospitalar.dto.medico.MedicoRequestDTO;
import com.JPA.SistemaHospitalar.dto.medico.MedicoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public Medico toEntity(MedicoRequestDTO dto) {
        return new Medico(dto.nome(), dto.especialidade(), dto.crm());
    }

    public MedicoResponseDTO toResponseDTO(Medico medico) {
        return new MedicoResponseDTO(
                medico.getId(),
                medico.getNome(),
                medico.getEspecialidade(),
                medico.getCrm()
        );
    }
}
