package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.filter.HumorFiltro;

public interface HumorRepositoryCustom extends JpaSpecificationExecutor<Humor>{
	Page<Humor> findAll(HumorFiltro filtro, Pageable paginacao);
}
