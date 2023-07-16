package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_copia")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCopia {
	@Id
	@Column(name = "id_usuario_copia")
	private UUID idUsuarioCopia;
	
	@Column(name = "id_usuario_origem")
	private UUID idUsuarioOrigem;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "nome_curso")
	private String nomeCurso;
	
	@Column(name = "tipo_curso")
	private String tipoCurso;
	
	@Column(name = "periodo")
	@Enumerated(EnumType.STRING)
	private PeriodoCurso periodo;
	
	@Column(name = "turma")
	private String turma;
	
	@Column(name = "prontuario")
	private String prontuario;
	
	@Column(name = "url_avatar")
	private String urlAvatar;
	
}
