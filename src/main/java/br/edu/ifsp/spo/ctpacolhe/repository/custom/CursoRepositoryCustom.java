package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import org.springframework.data.domain.Page;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Curso;

public interface CursoRepositoryCustom {
	Page<Curso> findAll(FiltroWrapper filtroWrapper);
}
