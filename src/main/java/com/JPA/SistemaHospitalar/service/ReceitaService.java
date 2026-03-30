package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Receita;
import com.JPA.SistemaHospitalar.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita salvar(Receita receita) {
        return receitaRepository.save(receita);
    }

    public Optional<Receita> obterPorId(Long id) {
        return receitaRepository.findById(id);
    }

    public List<Receita> obterTodos() {
        return receitaRepository.findAll();
    }

    public List<Receita> obterPorMedicamento(String medicamento) {
        return receitaRepository.findByMedicamento(medicamento);
    }

    public List<Receita> obterPorDuracaoDias(int dias) {
        return receitaRepository.findByDuracaoDias(dias);
    }

    public Receita atualizar(Long id, Receita receitaAtualizada) {
        return receitaRepository.findById(id).map(receita -> {
            receita.setMedicamento(receitaAtualizada.getMedicamento());
            receita.setDosagem(receitaAtualizada.getDosagem());
            receita.setDuracaoDias(receitaAtualizada.getDuracaoDias());
            return receitaRepository.save(receita);
        }).orElseThrow(() -> new RuntimeException("Receita não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new RuntimeException("Receita não encontrada com id: " + id);
        }
        receitaRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return receitaRepository.existsById(id);
    }
}

