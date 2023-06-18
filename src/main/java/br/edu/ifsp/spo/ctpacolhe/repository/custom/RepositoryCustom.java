package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import javax.persistence.TypedQuery;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;

public abstract class RepositoryCustom {
	
	protected void paginaQuery(FiltroWrapper filtroWrapper, TypedQuery<?> typedQuery) {
		typedQuery.setFirstResult(filtroWrapper.getPaginacao().getPageNumber() * filtroWrapper.getPaginacao().getPageSize());
		typedQuery.setMaxResults(filtroWrapper.getPaginacao().getPageSize());
	}

}
