package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.dto.paciente.PacienteRequestDTO;
import com.JPA.SistemaHospitalar.dto.paciente.PacienteResponseDTO;
import com.JPA.SistemaHospitalar.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criar(@Valid @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pacienteService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obterPorId(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteResponseDTO> obterPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(pacienteService.obterPorCpf(cpf));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<PacienteResponseDTO>> obterPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(pacienteService.obterPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody PacienteRequestDTO dto) {
        return ResponseEntity.ok(pacienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
