package br.edu.ifsp.spo.ctpacolhe.entity.filter;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AgendamentoSalaFiltro implements Filtro {
	private LocalDate dataAtendimentoInicial;
	private LocalDate dataAtendimentoFinal;
	
	public Boolean hasDataAtendimentoInicial() {
		return this.dataAtendimentoInicial != null;
	}
	
	public Boolean hasDataAtendimentoFinal() {
		return this.dataAtendimentoFinal != null;
	}
}
