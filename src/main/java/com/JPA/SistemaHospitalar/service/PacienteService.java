package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Paciente;
import com.JPA.SistemaHospitalar.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente salvar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> obterPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public List<Paciente> obterTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obterPorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public List<Paciente> obterPorNome(String nome) {
        return pacienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Paciente atualizar(Long id, Paciente pacienteAtualizado) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setNome(pacienteAtualizado.getNome());
            paciente.setCpf(pacienteAtualizado.getCpf());
            paciente.setTelefone(pacienteAtualizado.getTelefone());
            return pacienteRepository.save(paciente);
        }).orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return pacienteRepository.existsById(id);
    }
}

