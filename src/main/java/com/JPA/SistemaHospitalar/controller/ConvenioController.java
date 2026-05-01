package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.dto.convenio.ConvenioRequestDTO;
import com.JPA.SistemaHospitalar.dto.convenio.ConvenioResponseDTO;
import com.JPA.SistemaHospitalar.service.ConvenioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/convenios")
public class ConvenioController {

    private final ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        this.convenioService = convenioService;
    }

    @PostMapping
    public ResponseEntity<ConvenioResponseDTO> criar(@Valid @RequestBody ConvenioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(convenioService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConvenioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(convenioService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConvenioResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(convenioService.obterPorId(id));
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<ConvenioResponseDTO> obterPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok(convenioService.obterPorCnpj(cnpj));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ConvenioResponseDTO>> obterPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(convenioService.obterPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConvenioResponseDTO> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody ConvenioRequestDTO dto) {
        return ResponseEntity.ok(convenioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        convenioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
