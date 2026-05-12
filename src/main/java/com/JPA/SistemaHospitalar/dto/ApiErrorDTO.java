package com.JPA.SistemaHospitalar.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiErrorDTO {

    private List<String> erros;

    public ApiErrorDTO(String mensagem) {
        this.erros = Arrays.asList(mensagem);
    }

    public ApiErrorDTO(List<String> erros) {
        this.erros = erros;
    }
}
