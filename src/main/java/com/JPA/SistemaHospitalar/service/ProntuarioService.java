package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Paciente;
import com.JPA.SistemaHospitalar.Entity.Prontuario;
import com.JPA.SistemaHospitalar.dto.prontuario.ProntuarioRequestDTO;
import com.JPA.SistemaHospitalar.dto.prontuario.ProntuarioResponseDTO;
import com.JPA.SistemaHospitalar.exception.ResourceNotFoundException;
import com.JPA.SistemaHospitalar.mapper.ProntuarioMapper;
import com.JPA.SistemaHospitalar.repository.PacienteRepository;
import com.JPA.SistemaHospitalar.repository.ProntuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final ProntuarioMapper prontuarioMapper;

    public ProntuarioService(ProntuarioRepository prontuarioRepository,
                              PacienteRepository pacienteRepository,
                              ProntuarioMapper prontuarioMapper) {
        this.prontuarioRepository = prontuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.prontuarioMapper = prontuarioMapper;
    }

    public ProntuarioResponseDTO salvarParaPaciente(Long pacienteId, ProntuarioRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com id: " + pacienteId));
        Prontuario prontuario = prontuarioMapper.toEntity(dto);
        paciente.setProntuario(prontuario);
        pacienteRepository.save(paciente);
        return prontuarioMapper.toResponseDTO(prontuario);
    }

    public ProntuarioResponseDTO obterPorId(Long id) {
        return prontuarioRepository.findById(id)
                .map(prontuarioMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com id: " + id));
    }

    public List<ProntuarioResponseDTO> obterTodos() {
        return prontuarioRepository.findAll().stream()
                .map(prontuarioMapper::toResponseDTO)
                .toList();
    }

    public ProntuarioResponseDTO atualizar(Long id, ProntuarioRequestDTO dto) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com id: " + id));
        prontuario.setTipoSanguineo(dto.tipoSanguineo());
        prontuario.setAlergia(dto.alergia());
        prontuario.setObservacoes(dto.observacoes());
        return prontuarioMapper.toResponseDTO(prontuarioRepository.save(prontuario));
    }

    public void deletar(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prontuário não encontrado com id: " + id);
        }
        prontuarioRepository.deleteById(id);
    }
}
