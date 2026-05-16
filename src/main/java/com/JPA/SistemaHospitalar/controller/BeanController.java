package com.JPA.SistemaHospitalar.controller;

import com.JPA.SistemaHospitalar.entity.Paciente;
import com.JPA.SistemaHospitalar.dto.consulta.ConsultaResponseDTO;
import com.JPA.SistemaHospitalar.dto.paciente.PacienteResponseDTO;
import com.JPA.SistemaHospitalar.mapper.ConsultaMapper;
import com.JPA.SistemaHospitalar.mapper.PacienteMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bean")
public class BeanController {

    private final Paciente paciente;
    private final PacienteMapper pacienteMapper;
    private final ConsultaMapper consultaMapper;

    public BeanController(Paciente paciente,
                          PacienteMapper pacienteMapper,
                          ConsultaMapper consultaMapper) {
        this.paciente = paciente;
        this.pacienteMapper = pacienteMapper;
        this.consultaMapper = consultaMapper;
    }

    @GetMapping("/paciente")
    public ResponseEntity<PacienteResponseDTO> obterPaciente() {
        return ResponseEntity.ok(pacienteMapper.toResponseDTO(paciente));
    }

    @GetMapping("/consultas")
    public ResponseEntity<List<ConsultaResponseDTO>> obterConsultas() {
        List<ConsultaResponseDTO> consultas = paciente.getConsultas().stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/paciente-completo")
    public ResponseEntity<Map<String, Object>> obterPacienteCompleto() {
        PacienteResponseDTO pacienteDTO = pacienteMapper.toResponseDTO(paciente);
        List<ConsultaResponseDTO> consultasDTO = paciente.getConsultas().stream()
                .map(consultaMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(Map.of(
                "paciente", pacienteDTO,
                "consultas", consultasDTO
        ));
    }
}