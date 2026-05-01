package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Medico;
import com.JPA.SistemaHospitalar.dto.medico.MedicoRequestDTO;
import com.JPA.SistemaHospitalar.dto.medico.MedicoResponseDTO;
import com.JPA.SistemaHospitalar.exception.ResourceNotFoundException;
import com.JPA.SistemaHospitalar.mapper.MedicoMapper;
import com.JPA.SistemaHospitalar.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    public MedicoResponseDTO salvar(MedicoRequestDTO dto) {
        Medico medico = medicoMapper.toEntity(dto);
        return medicoMapper.toResponseDTO(medicoRepository.save(medico));
    }

    public MedicoResponseDTO obterPorId(Long id) {
        return medicoRepository.findById(id)
                .map(medicoMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));
    }

    public List<MedicoResponseDTO> obterTodos() {
        return medicoRepository.findAll().stream()
                .map(medicoMapper::toResponseDTO)
                .toList();
    }

    public MedicoResponseDTO obterPorCrm(String crm) {
        return medicoRepository.findByCrm(crm)
                .map(medicoMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com CRM: " + crm));
    }

    public List<MedicoResponseDTO> obterPorNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(medicoMapper::toResponseDTO)
                .toList();
    }

    public List<MedicoResponseDTO> obterPorEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade).stream()
                .map(medicoMapper::toResponseDTO)
                .toList();
    }

    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO dto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));
        medico.setNome(dto.nome());
        medico.setEspecialidade(dto.especialidade());
        medico.setCrm(dto.crm());
        return medicoMapper.toResponseDTO(medicoRepository.save(medico));
    }

    public void deletar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médico não encontrado com id: " + id);
        }
        medicoRepository.deleteById(id);
    }
}
