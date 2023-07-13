package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.HashSet;

import javax.persistence.CascadeType;
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
@Table(name = "pergunta")
@Builder
@Data
public class Pergunta {
	@Id
	@Column(name = "id_pergunta")
	private UUID idPergunta;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ordem")
	private Integer ordem;
	
	@Column(name = "id_tipo_resposta")
	private String idTipoResposta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_resposta", referencedColumnName = "id_tipo_resposta", updatable = false, insertable = false)
	private TipoResposta tipoResposta;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST })
	@JoinColumn(name = "id_pergunta", referencedColumnName = "id_pergunta", updatable = false, insertable = false)
	@Builder.Default
	private Set<PerguntaResposta> respostas = new HashSet<>();

	public List<Resposta> getRespostas() {
		List<PerguntaResposta> respostasOrdenadas = respostas.stream()
				.sorted(Comparator.comparing(PerguntaResposta::getOrdemResposta)).toList();
		return respostasOrdenadas.stream().map(resp -> resp.getResposta()).collect(Collectors.toList());
	}
}
