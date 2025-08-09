package com.murilo.segurancaAPI.service;

import com.murilo.segurancaAPI.controller.dto.AutenticacaoDTO;
import com.murilo.segurancaAPI.controller.dto.TokenResponseDTO;
import com.murilo.segurancaAPI.controller.dto.UsuarioDTO;
import com.murilo.segurancaAPI.controller.mappers.UsuarioMapper;
import com.murilo.segurancaAPI.entity.Usuario;
import com.murilo.segurancaAPI.repository.UsuarioRepository;
import com.murilo.segurancaAPI.security.CustomUserDetails;
import com.murilo.segurancaAPI.security.TokenService;
import com.murilo.segurancaAPI.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioValidator validator;
    private final TokenService tokenService;

    public ResponseEntity<Usuario> salvar(UsuarioDTO dto){
            Usuario usuario = mapper.toEntity(dto);
            String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
            usuario.setSenha(senhaCriptografada);
            validator.existeUsuarioCadastrado(usuario);
            validator.validarSenha(dto);
            usuarioRepository.save(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<TokenResponseDTO> login(AutenticacaoDTO dto){
        var loginSenha = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var autenticacao = this.authenticationManager.authenticate(loginSenha);

        var customUserDetails = (CustomUserDetails) autenticacao.getPrincipal();
        String token = tokenService.gerarToken(customUserDetails.getUsername(), customUserDetails.getUsuario().getRole());

        return ResponseEntity.ok(
                new TokenResponseDTO("Bearer", token, tokenService.getExpirationSeconds())
        );
    }
}
