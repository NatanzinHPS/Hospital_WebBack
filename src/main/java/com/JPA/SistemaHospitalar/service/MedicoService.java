package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.entity.Medico;
import com.JPA.SistemaHospitalar.dto.medico.MedicoRequestDTO;
import com.JPA.SistemaHospitalar.dto.medico.MedicoResponseDTO;
import com.JPA.SistemaHospitalar.exception.RegraNegocioException;
import com.JPA.SistemaHospitalar.exception.ResourceNotFoundException;
import com.JPA.SistemaHospitalar.mapper.MedicoMapper;
import com.JPA.SistemaHospitalar.repository.MedicoRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public MedicoResponseDTO salvar(MedicoRequestDTO dto) {
        if (medicoRepository.findByCrm(dto.crm()).isPresent()) {
            throw new RegraNegocioException("Já existe um médico com o CRM: " + dto.crm());
        }
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

    @Transactional
    public MedicoResponseDTO atualizar(Long id, MedicoRequestDTO dto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));

        medicoRepository.findByCrm(dto.crm())
                .filter(m -> !m.getId().equals(id))
                .ifPresent(m -> { throw new RegraNegocioException("Já existe um médico com o CRM: " + dto.crm()); });

        medico.setNome(dto.nome());
        medico.setEspecialidade(dto.especialidade());
        medico.setCrm(dto.crm());
        return medicoMapper.toResponseDTO(medicoRepository.save(medico));
    }

    @Transactional
    public void deletar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Médico não encontrado com id: " + id);
        }
        medicoRepository.deleteById(id);
    }
}