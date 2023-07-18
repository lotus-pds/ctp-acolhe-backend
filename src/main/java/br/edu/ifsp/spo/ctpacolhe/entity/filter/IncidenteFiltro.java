package br.edu.ifsp.spo.ctpacolhe.entity.filter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
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
	private String nome;
	private String email;
	private String nomeCurso;
	private String tipoCurso;
	private List<PeriodoCurso> periodo;
	private String turma;
	private String prontuario;
	
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
	
	public Boolean hasNome() {
		return this.nome != null && !this.nome.isEmpty();
	}
	
	public Boolean hasEmail() {
		return this.email != null && !this.email.isEmpty();
	}
	
	public Boolean hasNomeCurso() {
		return this.nomeCurso != null && !this.nomeCurso.isEmpty();
	}
	
	public Boolean hasTipoCurso() {
		return this.tipoCurso != null && !this.tipoCurso.isEmpty();
	}
	
	public Boolean hasPeriodo() {
		return this.periodo != null;
	}
	
	public Boolean hasTurma() {
		return this.turma != null && !this.turma.isEmpty();
	}
	
	public Boolean hasProntuario() {
		return this.prontuario != null && !this.prontuario.isEmpty();
	}
	
}
