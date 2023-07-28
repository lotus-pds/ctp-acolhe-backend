package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_incidente")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoIncidente {
	
	@Id
	@Column(name = "id_tipo_incidente")
	private UUID idTipoIncidente;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "ativo")
    private Boolean ativo;
	
	public boolean isAtivo() {
		return this.ativo != null && this.ativo;
	}
}
