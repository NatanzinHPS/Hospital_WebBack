package com.JPA.SistemaHospitalar.mapper;

import com.JPA.SistemaHospitalar.Entity.Receita;
import com.JPA.SistemaHospitalar.dto.receita.ReceitaRequestDTO;
import com.JPA.SistemaHospitalar.dto.receita.ReceitaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ReceitaMapper {

    public Receita toEntity(ReceitaRequestDTO dto) {
        return new Receita(dto.medicamento(), dto.dosagem(), dto.duracaoDias());
    }

    public ReceitaResponseDTO toResponseDTO(Receita receita) {
        return new ReceitaResponseDTO(
                receita.getId(),
                receita.getMedicamento(),
                receita.getDosagem(),
                receita.getDuracaoDias()
        );
    }
}
