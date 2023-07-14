package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "incidente")
@Builder
@Data
public class Incidente {
	@Id
	@Column(name = "id_incidente")
	private UUID idIncidente;

	@Column(name = "id_usuario_copia")
	private UUID idUsuarioCopia;

	@Column(name = "data_incidente")
	private LocalDateTime dataIncidente;

	@Column(name = "assunto")
	private String assunto;

	@Column(name = "id_status")
	private String idStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_copia", referencedColumnName = "id_usuario_copia", insertable = false, updatable = false)
	private UsuarioCopia usuarioCopia;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_incidente", referencedColumnName = "id_incidente")
	@Builder.Default
	private Set<IncidenteDetalhe> detalhes = new HashSet<>();

	public List<Pergunta> getPerguntasRespostas() {
		return detalhes.stream().map(deta -> {
			return Pergunta.builder().descricao(deta.getPergunta()).build();
		}).toList();
	}
}
