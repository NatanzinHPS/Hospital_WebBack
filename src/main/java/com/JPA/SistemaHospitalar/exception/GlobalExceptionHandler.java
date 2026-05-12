package com.JPA.SistemaHospitalar.exception;

import com.JPA.SistemaHospitalar.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erros de validacao de campos (@Valid / @NotBlank, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDTO handleValidationError(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ApiErrorDTO(erros);
    }

    // Erros de regra de negocio lancados nos Services
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorDTO handleRegraNegocio(RegraNegocioException ex) {
        return new ApiErrorDTO(ex.getMessage());
    }

    // Recurso nao encontrado (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorDTO handleNotFound(ResourceNotFoundException ex) {
        return new ApiErrorDTO(ex.getMessage());
    }

    // Qualquer outro erro nao tratado (500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorDTO handleGenericError(Exception ex) {
        return new ApiErrorDTO("Erro interno no servidor");
    }
}
