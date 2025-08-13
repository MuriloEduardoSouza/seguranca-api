package com.murilo.segurancaAPI.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex){
        return new ErroResposta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(UsuarioDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta tratarUsuarioDuplicado(UsuarioDuplicadoException ex){
        return new ErroResposta(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta senhaInvalida(SenhaInvalidaException ex){
            return new ErroResposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErroResposta auth(AuthenticationException ex){
        return new ErroResposta(HttpStatus.UNAUTHORIZED, "Não autenticado.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroResposta auth(AccessDeniedException ex){
        return new ErroResposta(HttpStatus.FORBIDDEN, "Sem permissão.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErroResposta badCreds(BadCredentialsException ex){
        return new ErroResposta(HttpStatus.UNAUTHORIZED, "Login ou senha inválidos");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        var erros = ex.getBindingResult()
                .getFieldErrors() // <- plural
                .stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();

        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "Dados inválidos.",
                erros
        );
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
