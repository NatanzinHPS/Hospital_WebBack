package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.dto.receita.ReceitaRequestDTO;
import com.JPA.SistemaHospitalar.dto.receita.ReceitaResponseDTO;
import com.JPA.SistemaHospitalar.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @PostMapping
    public ResponseEntity<ReceitaResponseDTO> criar(@Valid @RequestBody ReceitaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ReceitaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(receitaService.obterTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.obterPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaResponseDTO> atualizar(@PathVariable Long id,
                                                         @Valid @RequestBody ReceitaRequestDTO dto) {
        return ResponseEntity.ok(receitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
