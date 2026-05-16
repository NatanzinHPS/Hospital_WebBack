package com.JPA.SistemaHospitalar.repository;

import com.JPA.SistemaHospitalar.entity.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    List<Prontuario> findByTipoSanguineo(String tipoSanguineo);
    List<Prontuario> findByAlergiaContainingIgnoreCase(String alergia);
}

