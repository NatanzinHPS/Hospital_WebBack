package com.JPA.SistemaHospitalar.repository;

import com.JPA.SistemaHospitalar.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByMedicamento(String medicamento);
    List<Receita> findByDuracaoDias(int dias);
}

