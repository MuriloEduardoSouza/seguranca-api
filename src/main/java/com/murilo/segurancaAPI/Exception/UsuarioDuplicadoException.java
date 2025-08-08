package com.murilo.segurancaAPI.Exception;

public class UsuarioDuplicadoException extends RuntimeException{
    public UsuarioDuplicadoException(String mensagem){
        super(mensagem);
    }
}
