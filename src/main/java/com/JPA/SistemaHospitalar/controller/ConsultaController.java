package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.dto.consulta.ConsultaRequestDTO;
import com.JPA.SistemaHospitalar.dto.consulta.ConsultaResponseDTO;
import com.JPA.SistemaHospitalar.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> criar(@Valid @RequestBody ConsultaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(consultaService.obterTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.obterPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaResponseDTO>> obterPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(consultaService.obterPorPacienteId(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ConsultaResponseDTO>> obterPorMedico(@PathVariable Long medicoId) {
        return ResponseEntity.ok(consultaService.obterPorMedicoId(medicoId));
    }

    @GetMapping("/convenio/{convenioId}")
    public ResponseEntity<List<ConsultaResponseDTO>> obterPorConvenio(@PathVariable Long convenioId) {
        return ResponseEntity.ok(consultaService.obterPorConvenioId(convenioId));
    }

    @GetMapping("/motivo")
    public ResponseEntity<List<ConsultaResponseDTO>> obterPorMotivo(@RequestParam String motivo) {
        return ResponseEntity.ok(consultaService.obterPorMotivo(motivo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody ConsultaRequestDTO dto) {
        return ResponseEntity.ok(consultaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
