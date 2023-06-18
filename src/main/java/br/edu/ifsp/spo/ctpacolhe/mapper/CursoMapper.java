package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.ifsp.spo.ctpacolhe.dto.CursoDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {
	List<CursoDto> to(List<Curso> cursos);
}
