package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.ifsp.spo.ctpacolhe.dto.RespostaDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Resposta;

@Mapper(componentModel = "spring")
public interface RespostaMapper {
	RespostaDto to(Resposta resposta);
	List<RespostaDto> to(List<Resposta> respostas);
}
