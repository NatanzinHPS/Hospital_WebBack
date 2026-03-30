package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.Entity.Convenio;
import com.JPA.SistemaHospitalar.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/convenios")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @PostMapping
    public ResponseEntity<Convenio> criar(@RequestBody Convenio convenio) {
        Convenio convenioSalvo = convenioService.salvar(convenio);
        return ResponseEntity.status(HttpStatus.CREATED).body(convenioSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Convenio>> listarTodos() {
        List<Convenio> convenios = convenioService.obterTodos();
        return ResponseEntity.ok(convenios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Convenio> obterPorId(@PathVariable Long id) {
        Optional<Convenio> convenio = convenioService.obterPorId(id);
        return convenio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Convenio> obterPorCnpj(@PathVariable String cnpj) {
        Optional<Convenio> convenio = convenioService.obterPorCnpj(cnpj);
        return convenio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Convenio>> obterPorNome(@RequestParam String nome) {
        List<Convenio> convenios = convenioService.obterPorNome(nome);
        return ResponseEntity.ok(convenios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Convenio> atualizar(@PathVariable Long id, @RequestBody Convenio convenio) {
        try {
            Convenio convenioAtualizado = convenioService.atualizar(id, convenio);
            return ResponseEntity.ok(convenioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            convenioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

