package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="humor")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Humor {
	
	@Id
	@Column(name = "id_humor")
	private UUID idHumor;
	
	@Column(name = "data_humor")
	private LocalDate dataHumor;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_usuario")
	private UUID idUsuario;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "id_sentimento")
	private String idSentimento;

}
