package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Prontuario;
import com.JPA.SistemaHospitalar.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public Prontuario salvar(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public Optional<Prontuario> obterPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    public List<Prontuario> obterTodos() {
        return prontuarioRepository.findAll();
    }

    public List<Prontuario> obterPorTipoSanguineo(String tipoSanguineo) {
        return prontuarioRepository.findByTipoSanguineo(tipoSanguineo);
    }

    public List<Prontuario> obterPorAlergia(String alergia) {
        return prontuarioRepository.findByAlergiaContainingIgnoreCase(alergia);
    }

    public Prontuario atualizar(Long id, Prontuario prontuarioAtualizado) {
        return prontuarioRepository.findById(id).map(prontuario -> {
            prontuario.setTipoSanguineo(prontuarioAtualizado.getTipoSanguineo());
            prontuario.setAlergia(prontuarioAtualizado.getAlergia());
            prontuario.setObservacoes(prontuarioAtualizado.getObservacoes());
            return prontuarioRepository.save(prontuario);
        }).orElseThrow(() -> new RuntimeException("Prontuário não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        if (!prontuarioRepository.existsById(id)) {
            throw new RuntimeException("Prontuário não encontrado com id: " + id);
        }
        prontuarioRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return prontuarioRepository.existsById(id);
    }
}

