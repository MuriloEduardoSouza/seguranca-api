package com.murilo.segurancaAPI.validator;

import com.murilo.segurancaAPI.controller.dto.UsuarioDTO;
import com.murilo.segurancaAPI.entity.Usuario;
import com.murilo.segurancaAPI.exception.SenhaInvalidaException;
import com.murilo.segurancaAPI.exception.UsuarioDuplicadoException;
import com.murilo.segurancaAPI.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository repository;

    public void existeUsuarioCadastrado(Usuario usuario){
        if (usuario.getLogin() == null || usuario.getLogin().isBlank()){
            throw new IllegalArgumentException("Login é obrigatório");
        }

        boolean existe = repository.existsByLogin(usuario.getLogin());
            if(existe){
                throw new UsuarioDuplicadoException("Usuário ja existe.");
            }
    }

    public void validarSenha(UsuarioDTO dto){
        String senhaDigitada = dto.getSenha();

        if(senhaDigitada.length() < 6){
            throw new SenhaInvalidaException("A senha deve ter pelo menos 6 caracteres.");
        }

        else if(!senhaDigitada.matches(".*[A-Z].*")){
            throw new SenhaInvalidaException("A senha deve ter pelo menos 1 caractere maiúsculo.");
        }

        else if (!senhaDigitada.matches(".*[@#$%^&+=!].*")){
            throw new SenhaInvalidaException("A senha deve ter pelo menos 1 caractere especial.");
        }

        else if(!senhaDigitada.matches(".*\\d.*")){
            throw new SenhaInvalidaException(("A senha deve ter pelo menos 1 número."));
        }
    }
}
