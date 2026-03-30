package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.Entity.Consulta;
import com.JPA.SistemaHospitalar.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta) {
        Consulta consultaSalva = consultaService.salvar(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        List<Consulta> consultas = consultaService.obterTodas();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> obterPorId(@PathVariable Long id) {
        Optional<Consulta> consulta = consultaService.obterPorId(id);
        return consulta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Consulta>> obterPorPaciente(@PathVariable Long pacienteId) {
        List<Consulta> consultas = consultaService.obterPorPacienteId(pacienteId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consulta>> obterPorMedico(@PathVariable Long medicoId) {
        List<Consulta> consultas = consultaService.obterPorMedicoId(medicoId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/convenio/{convenioId}")
    public ResponseEntity<List<Consulta>> obterPorConvenio(@PathVariable Long convenioId) {
        List<Consulta> consultas = consultaService.obterPorConvenioId(convenioId);
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/motivo")
    public ResponseEntity<List<Consulta>> obterPorMotivo(@RequestParam String motivo) {
        List<Consulta> consultas = consultaService.obterPorMotivo(motivo);
        return ResponseEntity.ok(consultas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        try {
            Consulta consultaAtualizada = consultaService.atualizar(id, consulta);
            return ResponseEntity.ok(consultaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            consultaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

