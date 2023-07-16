package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.edu.ifsp.spo.ctpacolhe.common.util.CtpAcolheUtils;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente_;
import br.edu.ifsp.spo.ctpacolhe.entity.UsuarioCopia_;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.IncidenteFiltro;

public class IncidenteRepositoryCustomImpl extends RepositoryCustom implements IncidenteRepositoryCustom {

	@Override
	public Page<Incidente> findAllBy(FiltroWrapper filtroWrapper) {
		IncidenteFiltro filtro = (IncidenteFiltro) filtroWrapper.getFiltro();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Incidente> query = builder.createQuery(Incidente.class);
		Root<Incidente> root = query.from(Incidente.class);
		
		root.fetch(Incidente_.usuarioCopia, JoinType.LEFT);
		root.fetch(Incidente_.detalhes, JoinType.LEFT);
		root.fetch(Incidente_.status, JoinType.LEFT);
		
		List<Predicate> predicates = aplicaFiltros(filtro, root);
		
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.asc(root.get(Incidente_.dataIncidente)));
		
		TypedQuery<Incidente> typedQuery = entityManager.createQuery(query);
		
		List<Incidente> incidentes = new ArrayList<>();
		
		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}
		
		incidentes = typedQuery.getResultList();
		
		Long totalRegistros = countRegistros(builder, predicates);
		
		return new PageImpl<>(incidentes, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}
	
	private List<Predicate> aplicaFiltros(IncidenteFiltro filtro, Root<Incidente> root) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		List<Predicate> predicates = new ArrayList<>();
				
		if (filtro.hasAssunto()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.assunto)), "%" + filtro.getAssunto().toUpperCase() + "%"));
		}
		if (filtro.hasIdStatus()) {
			predicates.add(builder.equal(root.get(Incidente_.idStatus), filtro.getIdStatus()));
		}
		if (filtro.hasDataIncidenteInicial()) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Incidente_.dataIncidente), CtpAcolheUtils.toDateTimeAtStartOfDay(filtro.getDataIncidenteInicial())));
		}
		if (filtro.hasDataIncidenteFinal()) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Incidente_.dataIncidente), CtpAcolheUtils.toDateTimeAtEndOfDay(filtro.getDataIncidenteFinal())));
		}
		if (filtro.hasIdUsuarioOrigem()) {
			predicates.add(builder.equal(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.idUsuarioOrigem), filtro.getIdUsuarioOrigem()));
		}
		
		return predicates;
	}
	
	private Long countRegistros(CriteriaBuilder builder, List<Predicate> predicates) {
		CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
		qtdRegistros.select(builder.count(qtdRegistros.from(Incidente.class)));
		qtdRegistros.where(predicates.toArray(new Predicate[0]));
        
        Long totalRegistros = entityManager.createQuery(qtdRegistros).getSingleResult();
		return totalRegistros;
	}
}
