package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.edu.ifsp.spo.ctpacolhe.common.util.CtpAcolheUtils;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.AgendamentoSala;
import br.edu.ifsp.spo.ctpacolhe.entity.AgendamentoSala_;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario_;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.AgendamentoSalaFiltro;

public class AgendamentoSalaRepositoryCustomImpl extends RepositoryCustom implements AgendamentoSalaRepositoryCustom {
	
	@Override
	public Page<AgendamentoSala> findAll(FiltroWrapper filtroWrapper) {
		AgendamentoSalaFiltro filtro = (AgendamentoSalaFiltro) filtroWrapper.getFiltro();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AgendamentoSala> query = builder.createQuery(AgendamentoSala.class);
		Root<AgendamentoSala> root = query.from(AgendamentoSala.class);
		
		Fetch<AgendamentoSala, Usuario> fetchUsuario = root.fetch(AgendamentoSala_.criadoPor, JoinType.LEFT);
		fetchUsuario.fetch(Usuario_.curso, JoinType.LEFT);
		fetchUsuario.fetch(Usuario_.perfis, JoinType.LEFT);
		
		query.where(aplicaFiltros(filtro, root));
		query.orderBy(builder.asc(root.get(AgendamentoSala_.dataAtendimentoInicial)));
		
		TypedQuery<AgendamentoSala> typedQuery = entityManager.createQuery(query);
		
		List<AgendamentoSala> agendamentos = new ArrayList<>();
		
		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}
		
		agendamentos = typedQuery.getResultList();
		
		Long totalRegistros = countRegistros(filtro);
		
		return new PageImpl<>(agendamentos, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}
	
	private Predicate[] aplicaFiltros(AgendamentoSalaFiltro filtro, Root<AgendamentoSala> root) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filtro.hasDataAtendimentoInicial()) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(AgendamentoSala_.dataAtendimentoInicial), CtpAcolheUtils.toDateTimeAtStartOfDay(filtro.getDataAtendimentoInicial())));
		}
		if (filtro.hasDataAtendimentoFinal()) {
			predicates.add(builder.lessThanOrEqualTo(root.get(AgendamentoSala_.dataAtendimentoFinal), CtpAcolheUtils.toDateTimeAtEndOfDay(filtro.getDataAtendimentoFinal())));
		}
		
		return predicates.toArray(new Predicate[0]);
	}
	
	private Long countRegistros(AgendamentoSalaFiltro filtro) {
	    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
	    Root<AgendamentoSala> countRoot = qtdRegistros.from(AgendamentoSala.class);

	    Predicate[] predicates = aplicaFiltros(filtro, countRoot);
	    qtdRegistros.where(predicates);

	    qtdRegistros.select(builder.count(countRoot));

	    TypedQuery<Long> typedQuery = entityManager.createQuery(qtdRegistros);
	    return typedQuery.getSingleResult();
	}
	
}
