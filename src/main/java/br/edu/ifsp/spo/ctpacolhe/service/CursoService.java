package br.edu.ifsp.spo.ctpacolhe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Curso;
import br.edu.ifsp.spo.ctpacolhe.repository.CursoRepository;

@Service
@Transactional
public class CursoService {
	
	@Autowired
	private CursoRepository cursoRepository;

	public Page<Curso> buscaCursos(FiltroWrapper filtroWrapper) {
		Page<Curso> cursos = cursoRepository.findAll(filtroWrapper);
		return cursos;
	}
}
