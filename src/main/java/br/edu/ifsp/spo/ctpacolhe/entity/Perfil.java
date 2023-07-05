package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "perfil")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Perfil {
	
	@Id
	@Column(name = "id_perfil")
	private String idPerfil;
	@Column(name = "descricao")
	private String descricao;
	
	public Perfil(String descricao) {
		this.descricao = descricao;
	}
	
	public Perfil(PerfilUsuario perfil) {
		this.idPerfil = perfil.toString();
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idPerfil);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Perfil))
			return false;
		Perfil other = (Perfil) obj;
		return Objects.equals(idPerfil, other.idPerfil);
	}
}
