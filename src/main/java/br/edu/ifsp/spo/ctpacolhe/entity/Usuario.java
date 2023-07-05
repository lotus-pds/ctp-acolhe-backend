package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDateTime;
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
import javax.persistence.ManyToOne;
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
	@Column(name = "id_curso")
	private UUID idCurso;
	@Column(name = "periodo")
	@Enumerated(EnumType.STRING)
	private PeriodoCurso periodo;
	@Column(name = "turma")
	private String turma;
	@Column(name = "prontuario")
	private String prontuario;
	@Column(name = "senha")
	private String senha;
	@Column(name = "email_confirmado")
	@Builder.Default
	private Boolean emailConfirmado = false;
	@Column(name = "ativo")
	@Builder.Default
	private Boolean ativo = true;
	@Column(name = "data_cadastro")
	@Builder.Default
	private LocalDateTime dataCadastro = LocalDateTime.now();
	@Column(name = "url_avatar")
	private String urlAvatar;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso", referencedColumnName = "id_curso", insertable = false, updatable = false)
	private Curso curso;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "perfil_usuario", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	@Builder.Default
	private Set<Perfil> perfis = new HashSet<>();

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil);
	}
	
	public List<String> getPerfisString() {
		return this.getPerfis().stream().map(Perfil::getDescricao).toList();
	}
	
	public Perfil novoPerfil(String idPerfil) {
		Perfil novoPerfil = Perfil.builder().idPerfil(idPerfil).build();
		
		Perfil perfil = this.perfis.stream().filter(p -> p.equals(novoPerfil)).findFirst().orElse(novoPerfil);
		this.perfis.add(perfil);
		
		return perfil;
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
