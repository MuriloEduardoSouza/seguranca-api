package com.murilo.segurancaAPI.controller.mappers;

import com.murilo.segurancaAPI.controller.dto.UsuarioDTO;
import com.murilo.segurancaAPI.controller.dto.UsuarioResponseDTO;
import com.murilo.segurancaAPI.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

     Usuario toEntity(UsuarioDTO dto);

     UsuarioResponseDTO toResponse(Usuario usuario);
}
