package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor_;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.HumorFiltro;

@Repository
public class HumorRepositoryCustomImpl extends RepositoryCustom implements HumorRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Humor> findAll(FiltroWrapper filtroWrapper) {
		HumorFiltro filtro = (HumorFiltro) filtroWrapper.getFiltro();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Humor> query = builder.createQuery(Humor.class);
		Root<Humor> root = query.from(Humor.class);
		
		List<Predicate> predicates = aplicaFiltros(filtro, root);
		
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.desc(root.get(Humor_.dataHumor)));
		
		TypedQuery<Humor> typedQuery = entityManager.createQuery(query);
		
		List<Humor> humores = new ArrayList<>();
		
		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}
		
		humores = typedQuery.getResultList();
		
		Long totalRegistros = countRegistros(builder, predicates);

        return new PageImpl<>(humores, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}

	private List<Predicate> aplicaFiltros(HumorFiltro filtro, Root<Humor> root) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filtro.hasUsuario()) {
			predicates.add(builder.equal(root.get(Humor_.idUsuario), filtro.getUsuario().getIdUsuario()));
		}
		if (filtro.hasDataInicial()) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Humor_.dataHumor), filtro.getDataInicial()));
		}
		if (filtro.hasDataFinal()) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Humor_.dataHumor), filtro.getDataFinal()));
		}
		return predicates;
	}
	
	private Long countRegistros(CriteriaBuilder builder, List<Predicate> predicates) {
		CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
		qtdRegistros.select(builder.count(qtdRegistros.from(Humor.class)));
		qtdRegistros.where(predicates.toArray(new Predicate[0]));
        
        Long totalRegistros = entityManager.createQuery(qtdRegistros).getSingleResult();
		return totalRegistros;
	}
}
