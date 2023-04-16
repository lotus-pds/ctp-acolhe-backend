package br.edu.ifsp.spo.ctpacolhe.mapper;

import org.mapstruct.Mapper;

import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	UsuarioDto to(Usuario usuario);
}
