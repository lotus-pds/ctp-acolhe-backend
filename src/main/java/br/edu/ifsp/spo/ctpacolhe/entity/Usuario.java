package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifsp.spo.ctpacolhe.constant.PerfilUsuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = -9070815452483904355L;

	@Id
	@Column(name = "id_usuario")
	private UUID idUsuario;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "curso")
	private String curso;
	@Column(name = "periodo")
	private String periodo;
	@Column(name = "turma")
	private String turma;
	@Column(name = "perfil")
	@Enumerated(EnumType.STRING)
	private PerfilUsuario perfil;
	@Column(name = "senha")
	private String senha;
	
	public Usuario() {
		this.perfil = PerfilUsuario.ALUNO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
