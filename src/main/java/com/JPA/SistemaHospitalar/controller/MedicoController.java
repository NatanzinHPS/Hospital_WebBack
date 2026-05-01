package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.dto.medico.MedicoRequestDTO;
import com.JPA.SistemaHospitalar.dto.medico.MedicoResponseDTO;
import com.JPA.SistemaHospitalar.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> criar(@Valid @RequestBody MedicoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(medicoService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.obterPorId(id));
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<MedicoResponseDTO> obterPorCrm(@PathVariable String crm) {
        return ResponseEntity.ok(medicoService.obterPorCrm(crm));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<MedicoResponseDTO>> obterPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(medicoService.obterPorNome(nome));
    }

    @GetMapping("/especialidade")
    public ResponseEntity<List<MedicoResponseDTO>> obterPorEspecialidade(@RequestParam String especialidade) {
        return ResponseEntity.ok(medicoService.obterPorEspecialidade(especialidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody MedicoRequestDTO dto) {
        return ResponseEntity.ok(medicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
