package com.JPA.SistemaHospitalar.repository;

import com.JPA.SistemaHospitalar.Entity.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
    Optional<Convenio> findByCnpj(String cnpj);
    List<Convenio> findByNomeContainingIgnoreCase(String nome);
}

