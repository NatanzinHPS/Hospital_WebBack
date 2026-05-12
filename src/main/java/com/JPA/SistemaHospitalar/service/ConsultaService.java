package com.JPA.SistemaHospitalar.service;

import com.JPA.SistemaHospitalar.Entity.Consulta;
import com.JPA.SistemaHospitalar.Entity.Convenio;
import com.JPA.SistemaHospitalar.Entity.Medico;
import com.JPA.SistemaHospitalar.Entity.Paciente;
import com.JPA.SistemaHospitalar.Entity.Receita;
import com.JPA.SistemaHospitalar.dto.consulta.ConsultaRequestDTO;
import com.JPA.SistemaHospitalar.dto.consulta.ConsultaResponseDTO;
import com.JPA.SistemaHospitalar.exception.RegraNegocioException;
import com.JPA.SistemaHospitalar.mapper.ConsultaMapper;
import com.JPA.SistemaHospitalar.mapper.ReceitaMapper;
import com.JPA.SistemaHospitalar.repository.ConsultaRepository;
import com.JPA.SistemaHospitalar.repository.ConvenioRepository;
import com.JPA.SistemaHospitalar.repository.MedicoRepository;
import com.JPA.SistemaHospitalar.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ConvenioRepository convenioRepository;
    private final ConsultaMapper consultaMapper;
    private final ReceitaMapper receitaMapper;

    public ConsultaService(ConsultaRepository consultaRepository,
                            PacienteRepository pacienteRepository,
                            MedicoRepository medicoRepository,
                            ConvenioRepository convenioRepository,
                            ConsultaMapper consultaMapper,
                            ReceitaMapper receitaMapper) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.convenioRepository = convenioRepository;
        this.consultaMapper = consultaMapper;
        this.receitaMapper = receitaMapper;
    }

    public ConsultaResponseDTO salvar(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RegraNegocioException("Paciente não encontrado com id: " + dto.pacienteId()));
        Medico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new RegraNegocioException("Médico não encontrado com id: " + dto.medicoId()));
        Convenio convenio = convenioRepository.findById(dto.convenioId())
                .orElseThrow(() -> new RegraNegocioException("Convênio não encontrado com id: " + dto.convenioId()));

        Consulta consulta = new Consulta(dto.dataHora(), dto.motivo(), dto.valor());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setConvenio(convenio);

        if (dto.receita() != null) {
            Receita receita = receitaMapper.toEntity(dto.receita());
            consulta.setReceita(receita);
        }

        return consultaMapper.toResponseDTO(consultaRepository.save(consulta));
    }

    public ConsultaResponseDTO obterPorId(Long id) {
        return consultaRepository.findById(id)
                .map(consultaMapper::toResponseDTO)
                .orElseThrow(() -> new RegraNegocioException("Consulta não encontrada com id: " + id));
    }

    public List<ConsultaResponseDTO> obterTodas() {
        return consultaRepository.findAll().stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
    }

    public List<ConsultaResponseDTO> obterPorPacienteId(Long pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId).stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
    }

    public List<ConsultaResponseDTO> obterPorMedicoId(Long medicoId) {
        return consultaRepository.findByMedicoId(medicoId).stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
    }

    public List<ConsultaResponseDTO> obterPorConvenioId(Long convenioId) {
        return consultaRepository.findByConvenioId(convenioId).stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
    }

    public List<ConsultaResponseDTO> obterPorMotivo(String motivo) {
        return consultaRepository.findByMotivo(motivo).stream()
                .map(consultaMapper::toResponseDTO)
                .toList();
    }

    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Consulta não encontrada com id: " + id));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new RegraNegocioException("Paciente não encontrado com id: " + dto.pacienteId()));
        Medico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new RegraNegocioException("Médico não encontrado com id: " + dto.medicoId()));
        Convenio convenio = convenioRepository.findById(dto.convenioId())
                .orElseThrow(() -> new RegraNegocioException("Convênio não encontrado com id: " + dto.convenioId()));

        consulta.setDataHora(dto.dataHora());
        consulta.setMotivo(dto.motivo());
        consulta.setValor(dto.valor());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setConvenio(convenio);

        if (dto.receita() != null) {
            Receita receita = receitaMapper.toEntity(dto.receita());
            consulta.setReceita(receita);
        }

        return consultaMapper.toResponseDTO(consultaRepository.save(consulta));
    }

    public void deletar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new RegraNegocioException("Consulta não encontrada com id: " + id);
        }
        consultaRepository.deleteById(id);
    }
}
