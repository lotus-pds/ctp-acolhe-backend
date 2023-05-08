package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	@Enumerated(EnumType.STRING)
	private PeriodoCurso periodo;
	@Column(name = "turma")
	private String turma;
	@Column(name = "prontuario")
	private String prontuario;
	@Column(name = "senha")
	private String senha;
	@Column(name = "ativo")
	@Builder.Default
	private Boolean ativo = true;
	@Column(name = "email_confirmado")
	@Builder.Default
	private Boolean emailConfirmado = false;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "perfil_usuario", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	@Builder.Default
	private Set<Perfil> perfis = new HashSet<>();

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		perfis.forEach(perfil -> {
			authorities.add(new SimpleGrantedAuthority(perfil.getDescricao()));
		});
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
