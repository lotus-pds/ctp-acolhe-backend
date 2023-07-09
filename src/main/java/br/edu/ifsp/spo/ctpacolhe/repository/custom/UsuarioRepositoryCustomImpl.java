package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario_;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.UsuarioFiltro;

public class UsuarioRepositoryCustomImpl extends RepositoryCustom implements UsuarioRepositoryCustom {

	@Override
	public Page<Usuario> findAll(FiltroWrapper filtroWrapper) {
		UsuarioFiltro filtro = (UsuarioFiltro) filtroWrapper.getFiltro();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);
		
		root.fetch(Usuario_.curso, JoinType.LEFT);
		root.fetch(Usuario_.perfis, JoinType.LEFT);
		
		List<Predicate> predicates = aplicaFiltros(filtro, root);
		
		query.where(predicates.toArray(new Predicate[0]));
		query.orderBy(builder.asc(root.get(Usuario_.nome)));
		
		TypedQuery<Usuario> typedQuery = entityManager.createQuery(query);
		
		List<Usuario> usuarios = new ArrayList<>();
		
		if (filtroWrapper.hasPaginacao()) {
			paginaQuery(filtroWrapper, typedQuery);
		}
		
		usuarios = typedQuery.getResultList();
		
		Long totalRegistros = countRegistros(builder, predicates);

        return new PageImpl<>(usuarios, filtroWrapper.getPaginacao(totalRegistros), totalRegistros);
	}
	
	@Override
	public void removePerfisByIds(List<UUID> idsUsuarios) {		
		String deleteQuery = "DELETE FROM perfil_usuario WHERE id_usuario IN (:idsUsuarios)";
		
		Query query = entityManager.createNativeQuery(deleteQuery);
		query.setParameter("idsUsuarios", idsUsuarios);
		
		query.executeUpdate();
	}
	
	@Override
	public void removeHumoresByIds(List<UUID> idsUsuarios) {
		String deleteQuery = "DELETE FROM humor WHERE id_usuario IN (:idsUsuarios)";
		
		Query query = entityManager.createNativeQuery(deleteQuery);
		query.setParameter("idsUsuarios", idsUsuarios);
		
		query.executeUpdate();
	}

	private List<Predicate> aplicaFiltros(UsuarioFiltro filtro, Root<Usuario> root) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (filtro.hasNome()) {
			predicates.add(builder.like(builder.upper(root.get(Usuario_.nome)), "%" + filtro.getNome().toUpperCase() + "%"));
		}
		if (filtro.hasEmail()) {
			predicates.add(builder.like(builder.upper(root.get(Usuario_.email)), "%" + filtro.getEmail().toUpperCase() + "%"));
		}
		if (filtro.hasProntuario()) {
			predicates.add(builder.like(builder.upper(root.get(Usuario_.prontuario)), "%" + filtro.getProntuario().toUpperCase() + "%"));
		}
		if (filtro.hasAtivo()) {
			predicates.add(builder.equal(root.get(Usuario_.ativo), filtro.getAtivo()));
		}
		
		return predicates;
	}
	
	private Long countRegistros(CriteriaBuilder builder, List<Predicate> predicates) {
		CriteriaQuery<Long> qtdRegistros = builder.createQuery(Long.class);
		qtdRegistros.select(builder.count(qtdRegistros.from(Usuario.class)));
		qtdRegistros.where(predicates.toArray(new Predicate[0]));
        
        Long totalRegistros = entityManager.createQuery(qtdRegistros).getSingleResult();
		return totalRegistros;
	}
}
