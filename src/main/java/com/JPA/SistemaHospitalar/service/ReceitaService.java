package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Receita;
import com.JPA.SistemaHospitalar.dto.receita.ReceitaRequestDTO;
import com.JPA.SistemaHospitalar.dto.receita.ReceitaResponseDTO;
import com.JPA.SistemaHospitalar.exception.ResourceNotFoundException;
import com.JPA.SistemaHospitalar.mapper.ReceitaMapper;
import com.JPA.SistemaHospitalar.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ReceitaMapper receitaMapper;

    public ReceitaService(ReceitaRepository receitaRepository, ReceitaMapper receitaMapper) {
        this.receitaRepository = receitaRepository;
        this.receitaMapper = receitaMapper;
    }

    public ReceitaResponseDTO salvar(ReceitaRequestDTO dto) {
        Receita receita = receitaMapper.toEntity(dto);
        return receitaMapper.toResponseDTO(receitaRepository.save(receita));
    }

    public ReceitaResponseDTO obterPorId(Long id) {
        return receitaRepository.findById(id)
                .map(receitaMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
    }

    public List<ReceitaResponseDTO> obterTodas() {
        return receitaRepository.findAll().stream()
                .map(receitaMapper::toResponseDTO)
                .toList();
    }

    public ReceitaResponseDTO atualizar(Long id, ReceitaRequestDTO dto) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
        receita.setMedicamento(dto.medicamento());
        receita.setDosagem(dto.dosagem());
        receita.setDuracaoDias(dto.duracaoDias());
        return receitaMapper.toResponseDTO(receitaRepository.save(receita));
    }

    public void deletar(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receita não encontrada com id: " + id);
        }
        receitaRepository.deleteById(id);
    }
}
