package com.murilo.segurancaAPI.controller.dto;

import com.murilo.segurancaAPI.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotBlank(message = "campo obrigatório.")
    private String login;
    @NotBlank(message = "campo obrigatório.")
    private String senha;
    @NotNull(message = "campo obrigatório.")
    private Role role;
}
