package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "renovacao_token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RenovacaoToken {
	@Id
	@Column(name = "id_renovacao_token")
	private UUID idRenovacaoToken;
	@Column(name = "token")
	private String token;
	@Column(name = "id_usuario")
	private UUID idUsuario;
}
