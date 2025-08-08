package com.murilo.segurancaAPI.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
        @NotBlank(message = "campo obrigatório.")
        String login,
        @NotBlank(message = "campo obrigatório.")
        String senha
) {
}
