package com.murilo.segurancaAPI.controller;

import com.murilo.segurancaAPI.controller.dto.AutenticacaoDTO;
import com.murilo.segurancaAPI.controller.dto.TokenResponseDTO;
import com.murilo.segurancaAPI.controller.dto.UsuarioDTO;
import com.murilo.segurancaAPI.entity.Usuario;
import com.murilo.segurancaAPI.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioDTO dto){
        return usuarioService.salvar(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AutenticacaoDTO dto){
        return usuarioService.login(dto);
    }
}
