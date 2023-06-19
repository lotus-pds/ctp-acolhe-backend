package br.edu.ifsp.spo.ctpacolhe.entity.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsuarioFiltro implements Filtro {
	private String nome;
	private String email;
	private String prontuario;
	private Boolean ativo;
	
	public Boolean hasNome() {
		return this.nome != null && !this.nome.isEmpty();
	}
	
	public Boolean hasEmail() {
		return this.email != null && !this.email.isEmpty();
	}
	
	public Boolean hasProntuario() {
		return this.prontuario != null && !this.prontuario.isEmpty();
	}
	
	public Boolean hasAtivo() {
		return this.ativo != null;
	}
}
