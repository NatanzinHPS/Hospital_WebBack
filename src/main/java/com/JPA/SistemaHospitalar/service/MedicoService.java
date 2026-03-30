package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Medico;
import com.JPA.SistemaHospitalar.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Optional<Medico> obterPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public List<Medico> obterTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> obterPorCrm(String crm) {
        return medicoRepository.findByCrm(crm);
    }

    public List<Medico> obterPorNome(String nome) {
        return medicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Medico> obterPorEspecialidade(String especialidade) {
        return medicoRepository.findByEspecialidade(especialidade);
    }

    public Medico atualizar(Long id, Medico medicoAtualizado) {
        return medicoRepository.findById(id).map(medico -> {
            medico.setNome(medicoAtualizado.getNome());
            medico.setEspecialidade(medicoAtualizado.getEspecialidade());
            medico.setCrm(medicoAtualizado.getCrm());
            return medicoRepository.save(medico);
        }).orElseThrow(() -> new RuntimeException("Médico não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado com id: " + id);
        }
        medicoRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return medicoRepository.existsById(id);
    }
}

