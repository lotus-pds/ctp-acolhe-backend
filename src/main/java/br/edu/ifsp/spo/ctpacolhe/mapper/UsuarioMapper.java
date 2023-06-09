package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	@Mapping(target = "curso", source = "curso")
	UsuarioDto to(Usuario usuario);
	List<UsuarioDto> to(List<Usuario> usuarios);
}
