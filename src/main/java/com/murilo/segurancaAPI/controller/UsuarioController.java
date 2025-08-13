package com.murilo.segurancaAPI.controller;

import com.murilo.segurancaAPI.controller.dto.AutenticacaoDTO;
import com.murilo.segurancaAPI.controller.dto.TokenResponseDTO;
import com.murilo.segurancaAPI.controller.dto.UsuarioDTO;
import com.murilo.segurancaAPI.controller.dto.UsuarioResponseDTO;
import com.murilo.segurancaAPI.controller.mappers.UsuarioMapper;
import com.murilo.segurancaAPI.entity.Usuario;
import com.murilo.segurancaAPI.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioDTO dto){
        Usuario salvo = usuarioService.salvar(dto);
        URI location = UriUtil.fromCurrentRequestWithId(salvo.getId());
        return ResponseEntity
                .created(location)
                .body(mapper.toResponse(salvo));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AutenticacaoDTO dto){
        return usuarioService.login(dto);
    }
}
