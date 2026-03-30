package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Consulta;
import com.JPA.SistemaHospitalar.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Optional<Consulta> obterPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public List<Consulta> obterTodas() {
        return consultaRepository.findAll();
    }

    public List<Consulta> obterPorPacienteId(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    public List<Consulta> obterPorMedicoId(Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    public List<Consulta> obterPorConvenioId(Long convenioId) {
        return consultaRepository.findByConvenioId(convenioId);
    }

    public List<Consulta> obterPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByDataHoraBetween(inicio, fim);
    }

    public List<Consulta> obterPorMotivo(String motivo) {
        return consultaRepository.findByMotivo(motivo);
    }

    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setDataHora(consultaAtualizada.getDataHora());
            consulta.setMotivo(consultaAtualizada.getMotivo());
            consulta.setValor(consultaAtualizada.getValor());
            consulta.setPaciente(consultaAtualizada.getPaciente());
            consulta.setMedico(consultaAtualizada.getMedico());
            consulta.setConvenio(consultaAtualizada.getConvenio());
            consulta.setReceita(consultaAtualizada.getReceita());
            return consultaRepository.save(consulta);
        }).orElseThrow(() -> new RuntimeException("Consulta não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada com id: " + id);
        }
        consultaRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return consultaRepository.existsById(id);
    }
}

