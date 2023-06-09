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
@Table(name = "verificacao_email_token")
@NoArgsConstructor
@Getter
@Setter
public class VerificacaoEmailToken extends Tokens {
	@Id
	@Column(name = "id_verificacao_token")
	private UUID idVerificacaoToken;

	public VerificacaoEmailToken(Usuario usuario, Integer duracaoEmSegundos) {
		super(usuario, duracaoEmSegundos);
		this.idVerificacaoToken = UUID.randomUUID();
	}

}
