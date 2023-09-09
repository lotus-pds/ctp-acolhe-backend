package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoCurso;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.CursoFiltro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoFiltroDto implements FiltroDto {
	
	@Schema(example = "Técnico em Informática Integrado ao Ensino Médio")
	private String nome;
	
	@Schema(example = "TECNICO_INTEGRADO")
	private TipoCurso tipo;
	
	@Schema(example = "true")
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
