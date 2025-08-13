package com.murilo.segurancaAPI.exception;

public class UsuarioDuplicadoException extends RuntimeException{
    public UsuarioDuplicadoException(String mensagem){
        super(mensagem);
    }
}
