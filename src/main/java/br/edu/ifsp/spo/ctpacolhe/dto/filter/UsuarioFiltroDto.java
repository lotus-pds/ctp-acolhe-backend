package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.UsuarioFiltro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFiltroDto implements FiltroDto {
	
	@Schema(example = "Renato Silva")
	private String nome;
	
	@Schema(example = "renato.s@aluno.ifsp.edu.br")
	private String email;
	
	@Schema(example = "SP3047766")
	private String prontuario;
	
	@Schema(example = "true")
	private Boolean ativo;
	
	@Override
	public FiltroWrapper toWrapper(Pageable paginacao) {
		UsuarioFiltro usuarioFiltro = UsuarioFiltro.builder()
				.nome(nome)
				.email(email)
				.prontuario(prontuario)
				.ativo(ativo)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(usuarioFiltro).build();
	}
	
}