package br.edu.ifsp.spo.ctpacolhe.entity.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TipoIncidenteFiltro implements Filtro {
	private Boolean ativo;
	
	public Boolean hasAtivo() {
		return this.ativo != null;
	}
}
