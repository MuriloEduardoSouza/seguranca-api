package com.murilo.segurancaAPI.controller.dto;

public record TokenResponseDTO(
        String type,
        String token,
        long expiresIn
) {
}
