package com.JPA.SistemaHospitalar.config;

import com.JPA.SistemaHospitalar.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class HospitalConfiguration {

    @Bean
    public Prontuario prontuario() {
        return new Prontuario("A+", "Dipirona", "Paciente hipertenso, monitoramento mensal necessário");
    }

    @Bean
    public Medico medico() {
        return new Medico("Dr. João Silva", "Cardiologia", "CRM-SP-12345");
    }

    @Bean
    public Convenio convenio() {
        return new Convenio("Unimed", "12.345.678/0001-99");
    }

    @Bean
    public Receita receita() {
        return new Receita("Losartana 50mg", "1 comprimido ao dia, preferencialmente pela manhã", 30);
    }

    @Bean
    public Consulta consulta(Medico medico, Convenio convenio, Receita receita) {
        Consulta consulta = new Consulta(
                LocalDateTime.of(2025, 5, 16, 10, 0),
                "Consulta de rotina — acompanhamento cardiológico",
                250.00
        );
        consulta.setMedico(medico);
        consulta.setConvenio(convenio);
        consulta.setReceita(receita);
        return consulta;
    }

    @Bean
    public Paciente paciente(Prontuario prontuario, Consulta consulta) {
        Paciente paciente = new Paciente("Maria Oliveira", "123.456.789-00", "(11) 99999-0000");
        paciente.setProntuario(prontuario);
        consulta.setPaciente(paciente);
        paciente.setConsultas(List.of(consulta));

        return paciente;
    }
}