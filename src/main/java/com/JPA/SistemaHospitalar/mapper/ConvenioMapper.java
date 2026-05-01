package com.JPA.SistemaHospitalar.mapper;

import com.JPA.SistemaHospitalar.Entity.Convenio;
import com.JPA.SistemaHospitalar.dto.convenio.ConvenioRequestDTO;
import com.JPA.SistemaHospitalar.dto.convenio.ConvenioResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ConvenioMapper {

    public Convenio toEntity(ConvenioRequestDTO dto) {
        return new Convenio(dto.nome(), dto.cnpj());
    }

    public ConvenioResponseDTO toResponseDTO(Convenio convenio) {
        return new ConvenioResponseDTO(
                convenio.getId(),
                convenio.getNome(),
                convenio.getCnpj()
        );
    }
}
