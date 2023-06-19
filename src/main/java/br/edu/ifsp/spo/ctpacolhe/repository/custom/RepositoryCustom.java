package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;

public abstract class RepositoryCustom {

	@PersistenceContext
	protected EntityManager entityManager;

	protected void paginaQuery(FiltroWrapper filtroWrapper, TypedQuery<?> typedQuery) {
		typedQuery.setFirstResult(
				filtroWrapper.getPaginacao().getPageNumber() * filtroWrapper.getPaginacao().getPageSize());
		typedQuery.setMaxResults(filtroWrapper.getPaginacao().getPageSize());
	}
}
