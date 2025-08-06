package com.murilo.segurancaAPI.controller;

import com.murilo.segurancaAPI.controller.dto.UsuarioDTO;
import com.murilo.segurancaAPI.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody UsuarioDTO dto){
        return usuarioService.salvar(dto);
    }
}
