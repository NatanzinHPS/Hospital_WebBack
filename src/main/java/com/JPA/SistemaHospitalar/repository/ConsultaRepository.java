package com.JPA.SistemaHospitalar.repository;

import com.JPA.SistemaHospitalar.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByPacienteId(Long pacienteId);
    List<Consulta> findByMedicoId(Long medicoId);
    List<Consulta> findByConvenioId(Long convenioId);
    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Consulta> findByMotivo(String motivo);
}

