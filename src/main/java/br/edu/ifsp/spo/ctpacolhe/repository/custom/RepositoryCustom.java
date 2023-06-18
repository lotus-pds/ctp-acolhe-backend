package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageImpl;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.CtpAcolheEntity;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.Filtro;

public abstract class RepositoryCustom {

	@PersistenceContext
	protected EntityManager entityManager;

	protected void paginaQuery(FiltroWrapper filtroWrapper, TypedQuery<?> typedQuery) {
		typedQuery.setFirstResult(
				filtroWrapper.getPaginacao().getPageNumber() * filtroWrapper.getPaginacao().getPageSize());
		typedQuery.setMaxResults(filtroWrapper.getPaginacao().getPageSize());
	}

	protected <Entidade extends CtpAcolheEntity> PageImpl<Entidade> buscaPaginadoComFiltro(Class<Entidade> classe,
			FiltroWrapper filtroWrapper, Filtro filtro, Function<Root<Entidade>, List<Predicate>> aplicaFiltros, List<Order> order) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Entidade> query = builder.createQuery(classe);
		Root<Entidade> root = query.from(classe);

		List<Predicate> predicates = aplicaFiltros.apply(root);

		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(order);

		TypedQuery<Entidade> typedQuery = entityManager.createQuery(query);

		List<Entidade> CtpAcolheEntityes = new ArrayList<>();

		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}

		CtpAcolheEntityes = typedQuery.getResultList();

		Long totalRegistros = countRegistros(classe, builder, predicates);

		return new PageImpl<>(CtpAcolheEntityes, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}
	
		private <Entidade extends CtpAcolheEntity> Long countRegistros(Class<Entidade> classe, CriteriaBuilder builder, List<Predicate> predicates) {
		CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
		qtdRegistros.select(builder.count(qtdRegistros.from(classe)));
		qtdRegistros.where(predicates.toArray(new Predicate[0]));
        
        Long totalRegistros = entityManager.createQuery(qtdRegistros).getSingleResult();
		return totalRegistros;
	}

}
