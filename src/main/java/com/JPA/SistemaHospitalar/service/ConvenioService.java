package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Convenio;
import com.JPA.SistemaHospitalar.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;

    public Convenio salvar(Convenio convenio) {
        return convenioRepository.save(convenio);
    }

    public Optional<Convenio> obterPorId(Long id) {
        return convenioRepository.findById(id);
    }

    public List<Convenio> obterTodos() {
        return convenioRepository.findAll();
    }

    public Optional<Convenio> obterPorCnpj(String cnpj) {
        return convenioRepository.findByCnpj(cnpj);
    }

    public List<Convenio> obterPorNome(String nome) {
        return convenioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Convenio atualizar(Long id, Convenio convenioAtualizado) {
        return convenioRepository.findById(id).map(convenio -> {
            convenio.setNome(convenioAtualizado.getNome());
            convenio.setCnpj(convenioAtualizado.getCnpj());
            return convenioRepository.save(convenio);
        }).orElseThrow(() -> new RuntimeException("Convênio não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        if (!convenioRepository.existsById(id)) {
            throw new RuntimeException("Convênio não encontrado com id: " + id);
        }
        convenioRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return convenioRepository.existsById(id);
    }
}

