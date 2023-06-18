package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Curso;
import br.edu.ifsp.spo.ctpacolhe.entity.Curso_;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.CursoFiltro;

@Repository
public class CursoRepositoryCustomImpl extends RepositoryCustom implements CursoRepositoryCustom {

	@Override
	public Page<Curso> findAll(FiltroWrapper filtroWrapper) {
		CursoFiltro filtro = (CursoFiltro) filtroWrapper.getFiltro();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Curso> query = builder.createQuery(Curso.class);
		Root<Curso> root = query.from(Curso.class);
		
		List<Predicate> predicates = aplicaFiltros(filtro, root);
		
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.asc(root.get(Curso_.tipo)), builder.asc(root.get(Curso_.nome)));
		
		TypedQuery<Curso> typedQuery = entityManager.createQuery(query);
		
		List<Curso> cursos = new ArrayList<>();
		
		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}
		
		cursos = typedQuery.getResultList();
		
		Long totalRegistros = countRegistros(builder, predicates);

        return new PageImpl<>(cursos, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}
	
	private List<Predicate> aplicaFiltros(CursoFiltro filtro, Root<Curso> root) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filtro.hasNome()) {
			predicates.add(builder.like(builder.upper(root.get(Curso_.nome)), "%" + filtro.getNome().toUpperCase() + "%"));
		}
		if (filtro.hasTipo()) {
			predicates.add(builder.equal(root.get(Curso_.tipo), filtro.getTipo()));
		}
		if (filtro.hasAtivo()) {
			predicates.add(builder.equal(root.get(Curso_.ativo), filtro.getAtivo()));
		}
		
		return predicates;
	}
	
	private Long countRegistros(CriteriaBuilder builder, List<Predicate> predicates) {
		CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
		qtdRegistros.select(builder.count(qtdRegistros.from(Curso.class)));
		qtdRegistros.where(predicates.toArray(new Predicate[0]));
        
        Long totalRegistros = entityManager.createQuery(qtdRegistros).getSingleResult();
		return totalRegistros;
	}
}
