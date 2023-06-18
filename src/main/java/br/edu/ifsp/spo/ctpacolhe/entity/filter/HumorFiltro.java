package br.edu.ifsp.spo.ctpacolhe.entity.filter;

import java.time.LocalDate;

import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class HumorFiltro implements Filtro {
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private Usuario usuario;
	
	public Boolean hasDataInicial() {
		return this.dataInicial != null;
	}
	
	public Boolean hasDataFinal() {
		return this.dataFinal != null;
	}
	
	public Boolean hasUsuario() {
		return this.usuario != null;
	}
}
