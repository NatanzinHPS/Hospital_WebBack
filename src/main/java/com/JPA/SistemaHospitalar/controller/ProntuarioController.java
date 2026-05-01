package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.dto.prontuario.ProntuarioRequestDTO;
import com.JPA.SistemaHospitalar.dto.prontuario.ProntuarioResponseDTO;
import com.JPA.SistemaHospitalar.service.ProntuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @PostMapping("/paciente/{pacienteId}")
    public ResponseEntity<ProntuarioResponseDTO> criarParaPaciente(@PathVariable Long pacienteId,
                                                                    @Valid @RequestBody ProntuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prontuarioService.salvarParaPaciente(pacienteId, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProntuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(prontuarioService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProntuarioResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prontuarioService.obterPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioResponseDTO> atualizar(@PathVariable Long id,
                                                            @Valid @RequestBody ProntuarioRequestDTO dto) {
        return ResponseEntity.ok(prontuarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prontuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
