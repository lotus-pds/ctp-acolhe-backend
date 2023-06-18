package br.edu.ifsp.spo.ctpacolhe.entity.filter;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoCurso;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CursoFiltro implements Filtro {
	private String nome;
	private TipoCurso tipo;
	private Boolean ativo;
	
	public Boolean hasNome() {
		return this.nome != null && !this.nome.isEmpty();
	}
	
	public Boolean hasTipo() {
		return this.tipo != null;
	}
	
	public Boolean hasAtivo() {
		return this.ativo != null;
	}
}
