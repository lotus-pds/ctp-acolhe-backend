package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.Curso;
import br.edu.ifsp.spo.ctpacolhe.repository.custom.CursoRepositoryCustom;

public interface CursoRepository extends JpaRepository<Curso, UUID>, CursoRepositoryCustom {

}
