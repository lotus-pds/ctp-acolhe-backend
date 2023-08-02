package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.edu.ifsp.spo.ctpacolhe.common.util.CtpAcolheUtils;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente_;
import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente;
import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente_;
import br.edu.ifsp.spo.ctpacolhe.entity.UsuarioCopia_;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.IncidenteFiltro;

public class IncidenteRepositoryCustomImpl extends RepositoryCustom implements IncidenteRepositoryCustom {

	@Override
	public Page<Incidente> findAll(FiltroWrapper filtroWrapper) {
		IncidenteFiltro filtro = (IncidenteFiltro) filtroWrapper.getFiltro();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Incidente> query = builder.createQuery(Incidente.class);
		Root<Incidente> root = query.from(Incidente.class);
		
		root.fetch(Incidente_.tipos, JoinType.LEFT);
		root.fetch(Incidente_.usuarioCopia, JoinType.LEFT);
		root.fetch(Incidente_.detalhes, JoinType.LEFT);
		root.fetch(Incidente_.status, JoinType.LEFT);
		
		query.where(aplicaFiltros(filtro, root));
		query.orderBy(builder.asc(root.get(Incidente_.dataIncidente)));
		
		TypedQuery<Incidente> typedQuery = entityManager.createQuery(query);
		
		List<Incidente> incidentes = new ArrayList<>();
		
		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}
		
		incidentes = typedQuery.getResultList();
		
		Long totalRegistros = countRegistros(filtro);
		
		return new PageImpl<>(incidentes, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}
	
	private Predicate[] aplicaFiltros(IncidenteFiltro filtro, Root<Incidente> root) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		List<Predicate> predicates = new ArrayList<>();
				
		if (filtro.hasAssunto()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.assunto)), "%" + filtro.getAssunto().toUpperCase() + "%"));
		}
		if (filtro.hasIdTipoIncidente()) {
			SetJoin<Incidente, TipoIncidente> joinTipos = root.join(Incidente_.tipos);
			predicates.add(joinTipos.get(TipoIncidente_.idTipoIncidente).in(filtro.getIdTipoIncidente()));
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
		if (filtro.hasNome()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.nome)), "%" + filtro.getNome().toUpperCase() + "%"));
		}
		if (filtro.hasEmail()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.email)), "%" + filtro.getEmail().toUpperCase() + "%"));
		}
		if (filtro.hasNomeCurso()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.nomeCurso)), "%" + filtro.getNomeCurso().toUpperCase() + "%"));
		}
		if (filtro.hasTipoCurso()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.tipoCurso)), "%" + filtro.getTipoCurso().toUpperCase() + "%"));
		}
		if (filtro.hasPeriodo()) {
			predicates.add(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.periodo).in(filtro.getPeriodo()));
		}
		if (filtro.hasTurma()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.turma)), "%" + filtro.getTurma().toUpperCase() + "%"));
		}
		if (filtro.hasProntuario()) {
			predicates.add(builder.like(builder.upper(root.get(Incidente_.usuarioCopia).get(UsuarioCopia_.prontuario)), "%" + filtro.getProntuario().toUpperCase() + "%"));
		}
		
		return predicates.toArray(new Predicate[0]);
	}
	
	private Long countRegistros(IncidenteFiltro filtro) {
	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
	    Root<Incidente> countRoot = qtdRegistros.from(Incidente.class);

	    Predicate[] predicates = aplicaFiltros(filtro, countRoot);
	    qtdRegistros.where(predicates);

	    qtdRegistros.select(builder.count(countRoot));

	    TypedQuery<Long> typedQuery = entityManager.createQuery(qtdRegistros);
	    return typedQuery.getSingleResult();
	}
}
