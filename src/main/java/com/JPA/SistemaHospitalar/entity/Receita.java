package com.JPA.SistemaHospitalar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "receitas")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String medicamento;

    @Column(nullable = false)
    private String dosagem;

    @Column(nullable = false)
    private int duracaoDias;

    @OneToOne(mappedBy = "receita")
    private Consulta consulta;

    public Receita() {
    }

    public Receita(String medicamento, String dosagem, int duracaoDias) {
        this.medicamento = medicamento;
        this.dosagem = dosagem;
        this.duracaoDias = duracaoDias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public int getDuracaoDias() {
        return duracaoDias;
    }

    public void setDuracaoDias(int duracaoDias) {
        this.duracaoDias = duracaoDias;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}

