package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Tokens {
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

	public Tokens(Usuario usuario, Integer duracaoEmSegundos) {
		this.token = UUID.randomUUID();
		this.geradoEm = LocalDateTime.now();
		this.expiraEm = LocalDateTime.now().plusSeconds(duracaoEmSegundos);
		this.idUsuario = usuario.getIdUsuario();
	}
}
