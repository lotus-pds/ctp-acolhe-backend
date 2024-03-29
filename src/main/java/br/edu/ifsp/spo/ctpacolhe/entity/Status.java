package br.edu.ifsp.spo.ctpacolhe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
	@Id
	@Column(name = "id_status")
	private String idStatus;
	
	@Column(name = "descricao")
	private String descricao;
}
