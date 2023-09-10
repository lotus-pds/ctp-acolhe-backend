package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.IncidenteFiltro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ParameterObject
public class IncidenteFiltroDto implements FiltroDto {
	
	@Schema(example = "Estou com dificuldades nos estudos")
	private String assunto;
	
	@Schema(example = "e3114512-ccc9-4bac-abb3-ff7005e10ae2")
	private List<UUID> idTipoIncidente;
	
	@Schema(example = "PEN")
	private String idStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-08")
	private LocalDate dataIncidenteInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-09")
	private LocalDate dataIncidenteFinal;
	
	@Schema(example = "Alessandra Josefa Sophia Dias")
	private String nome;
	
	@Schema(example = "@aluno.ifsp.edu.br")
	private String email;
	
	@Schema(example = "Informática")
	private String nomeCurso;
	
	@Schema(example = "Técnico Integrado ao Ensino Médio")
	private String tipoCurso;
	
	@Schema(example = "MATUTINO")
	private List<PeriodoCurso> periodo;
	
	@Schema(example = "413")
	private String turma;
	
	@Schema(example = "SP3047766")
	private String prontuario;
	
	@Override
	public FiltroWrapper toWrapper(Pageable paginacao) {
		IncidenteFiltro incidenteFiltro = IncidenteFiltro.builder()
				.assunto(assunto)
				.idTipoIncidente(idTipoIncidente)
				.idStatus(idStatus)
				.dataIncidenteInicial(dataIncidenteInicial)
				.dataIncidenteFinal(dataIncidenteFinal)
				.nome(nome)
				.email(email)
				.nomeCurso(nomeCurso)
				.tipoCurso(tipoCurso)
				.periodo(periodo)
				.turma(turma)
				.prontuario(prontuario)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(incidenteFiltro).build();
	}
	
}
