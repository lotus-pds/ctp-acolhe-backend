package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.UsuarioFiltro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFiltroDto implements FiltroDto {
	private String nome;
	private String email;
	private String prontuario;
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
