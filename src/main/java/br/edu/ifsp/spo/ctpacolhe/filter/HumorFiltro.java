package br.edu.ifsp.spo.ctpacolhe.filter;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HumorFiltro {
	
	private UUID idUsuario;
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate dataHumor;
	
	public Boolean hasIdUsuario() {
		return this.idUsuario != null;
	}
	
	public Boolean hasDataHumor() {
		return this.dataHumor != null;
	}
}
