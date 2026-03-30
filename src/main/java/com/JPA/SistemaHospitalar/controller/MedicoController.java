package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.Entity.Medico;
import com.JPA.SistemaHospitalar.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<Medico> criar(@RequestBody Medico medico) {
        Medico medicoSalvo = medicoService.salvar(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        List<Medico> medicos = medicoService.obterTodos();
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> obterPorId(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.obterPorId(id);
        return medico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<Medico> obterPorCrm(@PathVariable String crm) {
        Optional<Medico> medico = medicoService.obterPorCrm(crm);
        return medico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Medico>> obterPorNome(@RequestParam String nome) {
        List<Medico> medicos = medicoService.obterPorNome(nome);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/especialidade")
    public ResponseEntity<List<Medico>> obterPorEspecialidade(@RequestParam String especialidade) {
        List<Medico> medicos = medicoService.obterPorEspecialidade(especialidade);
        return ResponseEntity.ok(medicos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        try {
            Medico medicoAtualizado = medicoService.atualizar(id, medico);
            return ResponseEntity.ok(medicoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            medicoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

