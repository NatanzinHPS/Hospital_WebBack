package com.JPA.SistemaHospitalar.repository;

import com.JPA.SistemaHospitalar.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByCrm(String crm);
    List<Medico> findByNomeContainingIgnoreCase(String nome);
    List<Medico> findByEspecialidade(String especialidade);
}

