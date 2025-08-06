package com.murilo.segurancaAPI.controller.dto;

import com.murilo.segurancaAPI.entity.Role;
import lombok.Data;

@Data
public class UsuarioDTO {

    private String login;
    private String senha;
    private Role role;
}
