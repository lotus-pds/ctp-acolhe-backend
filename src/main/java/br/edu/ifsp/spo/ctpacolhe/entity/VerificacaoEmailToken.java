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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "verificacao_email_token")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VerificacaoEmailToken  {
	
	@Id
	@Column(name = "id_verificacao_token")
	private UUID idVerificacaoToken;
	@Column(name = "token")
	private UUID token;
	@Column(name = "gerado_em")
	private LocalDateTime geradoEm;
	@Column(name = "expira_em")
	private LocalDateTime expiraEm;
	@Column(name = "id_usuario")
	private UUID idUsuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
	private Usuario usuario;
	
	public VerificacaoEmailToken(Usuario usuario, Integer duracaoEmSegundos) {
        this.idVerificacaoToken = UUID.randomUUID();
        this.token = UUID.randomUUID();
        this.geradoEm = LocalDateTime.now();
        this.expiraEm = LocalDateTime.now().plusSeconds(duracaoEmSegundos);
        this.idUsuario = usuario.getIdUsuario();
    }
}
