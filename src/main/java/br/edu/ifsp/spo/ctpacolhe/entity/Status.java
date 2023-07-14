package br.edu.ifsp.spo.ctpacolhe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "status")
@Builder
@Data
public class Status {
	@Id
	@Column(name = "id_status")
	private String idStatus;
	
	@Column(name = "descricao")
	private String descricao;
}
