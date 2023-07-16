package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioCopiaDto;
import br.edu.ifsp.spo.ctpacolhe.entity.UsuarioCopia;

@Mapper
public interface UsuarioCopiaMapper {
	@Mapping(target = "periodo", source = "periodo")
	UsuarioCopiaDto to(UsuarioCopia usuarioCopia);
	List<UsuarioCopiaDto> to(List<UsuarioCopia> usuariosCopia);
}

