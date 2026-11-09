package com.paccanaro.finora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratamentoExcecao  {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> tratarErroValidacao(MethodArgumentNotValidException erro) {

        Map<String, String> erros = new HashMap<>();

        erro.getBindingResult().getFieldErrors().forEach(
                campo -> erros.put(campo.getField(), campo.getDefaultMessage())
        );

        return erros;
    }
}

