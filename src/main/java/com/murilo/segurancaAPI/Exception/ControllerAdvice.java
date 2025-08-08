package com.murilo.segurancaAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ErroResposta tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex){
        return new ErroResposta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioDuplicadoException.class)
    public ErroResposta tratarUsuarioDuplicado(UsuarioDuplicadoException ex){
        return new ErroResposta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ErroResposta senhaInvalida(SenhaInvalidaException ex){
            return new ErroResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta tratarErroGenerico(Exception ex){
        return new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado."
        );
    }
}
