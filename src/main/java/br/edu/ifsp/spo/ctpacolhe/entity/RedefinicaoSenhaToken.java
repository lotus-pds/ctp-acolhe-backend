package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "redefinicao_senha_token")
@NoArgsConstructor
@Getter
@Setter
public class RedefinicaoSenhaToken extends Tokens {
	@Id
	@Column(name = "id_redefinicao_token")
	private UUID idRedefinicaoToken;

	public RedefinicaoSenhaToken(Usuario usuario, Integer duracaoEmSegundos) {
		super(usuario, duracaoEmSegundos);
		this.idRedefinicaoToken = UUID.randomUUID();
	}
}
