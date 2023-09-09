package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "agendamento_sala")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoSala {
	@Id
	@Column(name = "id_agendamento")
	private UUID idAgendamento;
	
	@Column(name = "nome_alunos")
	private String nomeAlunos;

	@Column(name = "nome_tecnico")
	private String nomeTecnico;
	
	@Column(name = "data_atendimento_inicial")
	private LocalDateTime dataAtendimentoInicial;
	
	@Column(name = "data_atendimento_final")
	private LocalDateTime dataAtendimentoFinal;
	
	@Column(name = "id_criado_por")
	private UUID idCriadoPor;
	
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_criado_por", referencedColumnName = "id_usuario", insertable = false, updatable = false)
	private Usuario criadoPor;
	
}
