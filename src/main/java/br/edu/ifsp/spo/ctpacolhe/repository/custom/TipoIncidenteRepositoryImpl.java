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
import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente;
import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente_;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.TipoIncidenteFiltro;

@Repository
public class TipoIncidenteRepositoryImpl extends RepositoryCustom implements TipoIncidenteRepositoryCustom {

	@Override
	public Page<TipoIncidente> findAll(FiltroWrapper filtroWrapper) {
		TipoIncidenteFiltro filtro = (TipoIncidenteFiltro) filtroWrapper.getFiltro();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoIncidente> query = builder.createQuery(TipoIncidente.class);
		Root<TipoIncidente> root = query.from(TipoIncidente.class);
		
		List<Predicate> predicates = aplicaFiltros(filtro, root);
		
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.asc(root.get(TipoIncidente_.tipo)));
		
		TypedQuery<TipoIncidente> typedQuery = entityManager.createQuery(query);
		
		List<TipoIncidente> cursos = new ArrayList<>();
		
		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}
		
		cursos = typedQuery.getResultList();
		
		Long totalRegistros = countRegistros(builder, predicates);

        return new PageImpl<>(cursos, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}
	
	private List<Predicate> aplicaFiltros(TipoIncidenteFiltro filtro, Root<TipoIncidente> root) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filtro.hasAtivo()) {
			predicates.add(builder.equal(root.get(TipoIncidente_.ativo), filtro.getAtivo()));
		}
		
		return predicates;
	}
	
	private Long countRegistros(CriteriaBuilder builder, List<Predicate> predicates) {
		CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
		qtdRegistros.select(builder.count(qtdRegistros.from(TipoIncidente.class)));
		qtdRegistros.where(predicates.toArray(new Predicate[0]));
        
        Long totalRegistros = entityManager.createQuery(qtdRegistros).getSingleResult();
		return totalRegistros;
	}

}
