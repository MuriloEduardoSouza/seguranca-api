package com.murilo.segurancaAPI.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(
        int status,
        String error,
        String mensagem,
        List<String> erros
){
    public ErroResposta(HttpStatus status, String mensagem){
        this(status.value(), status.name(), mensagem, List.of());
    }

}
