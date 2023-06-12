package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.filter.HumorFiltro;

public class HumorRepositoryCustomImpl implements HumorRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Humor> findAll(Pageable paginacao, HumorFiltro filtro) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Humor> query = builder.createQuery(Humor.class);
        Root<Humor> root = query.from(Humor.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filtro.hasIdUsuario()) {
            predicates.add(builder.equal(root.get("idUsuario"), filtro.getIdUsuario()));
        }
        if (filtro.hasDataHumor()) {
        	predicates.add(builder.equal(root.get("dataHumor"), filtro.getDataHumor()));
        }

        query.where(predicates.toArray(new Predicate[0]));

        List<Order> orders = new ArrayList<>();
        orders.add(builder.desc(root.get("dataHumor")));

        query.orderBy(orders);

        return findAll(query, paginacao);
	}
	
}
