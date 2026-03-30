package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.Entity.Prontuario;
import com.JPA.SistemaHospitalar.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<Prontuario> criar(@RequestBody Prontuario prontuario) {
        Prontuario prontuarioSalvo = prontuarioService.salvar(prontuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(prontuarioSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Prontuario>> listarTodos() {
        List<Prontuario> prontuarios = prontuarioService.obterTodos();
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> obterPorId(@PathVariable Long id) {
        Optional<Prontuario> prontuario = prontuarioService.obterPorId(id);
        return prontuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tipoSanguineo")
    public ResponseEntity<List<Prontuario>> obterPorTipoSanguineo(@RequestParam String tipo) {
        List<Prontuario> prontuarios = prontuarioService.obterPorTipoSanguineo(tipo);
        return ResponseEntity.ok(prontuarios);
    }

    @GetMapping("/alergia")
    public ResponseEntity<List<Prontuario>> obterPorAlergia(@RequestParam String alergia) {
        List<Prontuario> prontuarios = prontuarioService.obterPorAlergia(alergia);
        return ResponseEntity.ok(prontuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> atualizar(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        try {
            Prontuario prontuarioAtualizado = prontuarioService.atualizar(id, prontuario);
            return ResponseEntity.ok(prontuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            prontuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

