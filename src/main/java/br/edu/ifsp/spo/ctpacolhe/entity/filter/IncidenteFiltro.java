package br.edu.ifsp.spo.ctpacolhe.entity.filter;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class IncidenteFiltro implements Filtro {
	private String assunto;
	private String idStatus;
	private LocalDate dataIncidenteInicial;
	private LocalDate dataIncidenteFinal;
	private UUID idUsuarioOrigem;
	
	public Boolean hasAssunto() {
		return this.assunto != null && !this.assunto.isEmpty();
	}
	
	public Boolean hasIdStatus() {
		return this.idStatus != null && !this.idStatus.isEmpty();
	}
	
	public Boolean hasDataIncidenteInicial() {
		return this.dataIncidenteInicial != null;
	}
	
	public Boolean hasDataIncidenteFinal() {
		return this.dataIncidenteFinal != null;
	}
	
	public Boolean hasIdUsuarioOrigem() {
		return this.idUsuarioOrigem != null;
	}
}
