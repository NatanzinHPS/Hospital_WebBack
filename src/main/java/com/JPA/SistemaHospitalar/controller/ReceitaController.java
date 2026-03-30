package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.Entity.Receita;
import com.JPA.SistemaHospitalar.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<Receita> criar(@RequestBody Receita receita) {
        Receita receitaSalva = receitaService.salvar(receita);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Receita>> listarTodas() {
        List<Receita> receitas = receitaService.obterTodos();
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> obterPorId(@PathVariable Long id) {
        Optional<Receita> receita = receitaService.obterPorId(id);
        return receita.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/medicamento")
    public ResponseEntity<List<Receita>> obterPorMedicamento(@RequestParam String medicamento) {
        List<Receita> receitas = receitaService.obterPorMedicamento(medicamento);
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/duracaoDias")
    public ResponseEntity<List<Receita>> obterPorDuracaoDias(@RequestParam int dias) {
        List<Receita> receitas = receitaService.obterPorDuracaoDias(dias);
        return ResponseEntity.ok(receitas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @RequestBody Receita receita) {
        try {
            Receita receitaAtualizada = receitaService.atualizar(id, receita);
            return ResponseEntity.ok(receitaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            receitaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

