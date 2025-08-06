package com.murilo.segurancaAPI.service;

import com.murilo.segurancaAPI.controller.dto.UsuarioDTO;
import com.murilo.segurancaAPI.controller.mappers.UsuarioMapper;
import com.murilo.segurancaAPI.entity.Usuario;
import com.murilo.segurancaAPI.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> salvar(UsuarioDTO dto){
        Usuario usuario = mapper.toEntity(dto);
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }
}
