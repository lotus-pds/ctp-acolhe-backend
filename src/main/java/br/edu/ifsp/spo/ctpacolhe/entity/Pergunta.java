package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoResposta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pergunta")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pergunta {
	@Id
	@Column(name = "id_pergunta")
	private UUID idPergunta;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ordem")
	private Integer ordem;
	
	@Column(name = "tipo_resposta")
	@Enumerated(EnumType.STRING)
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
