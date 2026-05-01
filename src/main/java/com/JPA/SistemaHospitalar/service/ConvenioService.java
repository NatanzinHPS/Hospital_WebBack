package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Convenio;
import com.JPA.SistemaHospitalar.dto.convenio.ConvenioRequestDTO;
import com.JPA.SistemaHospitalar.dto.convenio.ConvenioResponseDTO;
import com.JPA.SistemaHospitalar.exception.ResourceNotFoundException;
import com.JPA.SistemaHospitalar.mapper.ConvenioMapper;
import com.JPA.SistemaHospitalar.repository.ConvenioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioService {

    private final ConvenioRepository convenioRepository;
    private final ConvenioMapper convenioMapper;

    public ConvenioService(ConvenioRepository convenioRepository, ConvenioMapper convenioMapper) {
        this.convenioRepository = convenioRepository;
        this.convenioMapper = convenioMapper;
    }

    public ConvenioResponseDTO salvar(ConvenioRequestDTO dto) {
        Convenio convenio = convenioMapper.toEntity(dto);
        return convenioMapper.toResponseDTO(convenioRepository.save(convenio));
    }

    public ConvenioResponseDTO obterPorId(Long id) {
        return convenioRepository.findById(id)
                .map(convenioMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio não encontrado com id: " + id));
    }

    public List<ConvenioResponseDTO> obterTodos() {
        return convenioRepository.findAll().stream()
                .map(convenioMapper::toResponseDTO)
                .toList();
    }

    public ConvenioResponseDTO obterPorCnpj(String cnpj) {
        return convenioRepository.findByCnpj(cnpj)
                .map(convenioMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio não encontrado com CNPJ: " + cnpj));
    }

    public List<ConvenioResponseDTO> obterPorNome(String nome) {
        return convenioRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(convenioMapper::toResponseDTO)
                .toList();
    }

    public ConvenioResponseDTO atualizar(Long id, ConvenioRequestDTO dto) {
        Convenio convenio = convenioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio não encontrado com id: " + id));
        convenio.setNome(dto.nome());
        convenio.setCnpj(dto.cnpj());
        return convenioMapper.toResponseDTO(convenioRepository.save(convenio));
    }

    public void deletar(Long id) {
        if (!convenioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Convênio não encontrado com id: " + id);
        }
        convenioRepository.deleteById(id);
    }
}
