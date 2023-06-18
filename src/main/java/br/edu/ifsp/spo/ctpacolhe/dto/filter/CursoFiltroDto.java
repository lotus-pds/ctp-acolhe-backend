package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoCurso;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.CursoFiltro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoFiltroDto implements FiltroDto {
	private String nome;
	private TipoCurso tipo;
	private Boolean ativo;

	@Override
	public FiltroWrapper toWrapper(Pageable paginacao) {
		CursoFiltro cursoFiltro = CursoFiltro.builder()
				.nome(nome)
				.tipo(tipo)
				.ativo(ativo)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(cursoFiltro).build();
	}
}
