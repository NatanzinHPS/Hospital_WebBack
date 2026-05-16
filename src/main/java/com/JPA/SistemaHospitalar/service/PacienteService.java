package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.entity.Paciente;
import com.JPA.SistemaHospitalar.dto.paciente.PacienteRequestDTO;
import com.JPA.SistemaHospitalar.dto.paciente.PacienteResponseDTO;
import com.JPA.SistemaHospitalar.exception.RegraNegocioException;
import com.JPA.SistemaHospitalar.exception.ResourceNotFoundException;
import com.JPA.SistemaHospitalar.mapper.PacienteMapper;
import com.JPA.SistemaHospitalar.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    @Transactional
    public PacienteResponseDTO salvar(PacienteRequestDTO dto) {
        if (pacienteRepository.findByCpf(dto.cpf()).isPresent()) {
            throw new RegraNegocioException("Já existe um paciente com o CPF: " + dto.cpf());
        }
        Paciente paciente = pacienteMapper.toEntity(dto);
        return pacienteMapper.toResponseDTO(pacienteRepository.save(paciente));
    }

    public PacienteResponseDTO obterPorId(Long id) {
        return pacienteRepository.findById(id)
                .map(pacienteMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));
    }

    public List<PacienteResponseDTO> obterTodos() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::toResponseDTO)
                .toList();
    }

    public PacienteResponseDTO obterPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .map(pacienteMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com CPF: " + cpf));
    }

    public List<PacienteResponseDTO> obterPorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(pacienteMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + id));

        pacienteRepository.findByCpf(dto.cpf())
                .filter(p -> !p.getId().equals(id))
                .ifPresent(p -> { throw new RegraNegocioException("Já existe um paciente com o CPF: " + dto.cpf()); });

        paciente.setNome(dto.nome());
        paciente.setCpf(dto.cpf());
        paciente.setTelefone(dto.telefone());
        return pacienteMapper.toResponseDTO(pacienteRepository.save(paciente));
    }

    @Transactional
    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }
}