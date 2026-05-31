package com.vendinhas.vendas_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> tratarErroValidacao(MethodArgumentNotValidException ex) {
        List<FieldError> errosDoSpring = ex.getBindingResult().getFieldErrors();

        List<CampoErro> camposInvalidos = errosDoSpring.stream()
                .map(erro -> new CampoErro(erro.getField(), erro.getDefaultMessage()))
                .toList();

        ErroValidacaoResponse response = new ErroValidacaoResponse(
                400,
                "Erro de validação",
                "Existem campos inválidos na requisição.",
                camposInvalidos
        );

        return ResponseEntity.badRequest().body(response);
    }

    public record ErroValidacaoResponse(
            Integer status,
            String erro,
            String mensagem,
            List<CampoErro> campos
    ) {}

    public record CampoErro(
            String campo,
            String mensagem
    ) {}
}