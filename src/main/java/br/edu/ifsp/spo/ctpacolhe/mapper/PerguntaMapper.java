package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.edu.ifsp.spo.ctpacolhe.dto.PerguntaDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Pergunta;

@Mapper(componentModel = "spring")
public interface PerguntaMapper {
	
	RespostaMapper RESPOSTA_MAPPER = Mappers.getMapper(RespostaMapper.class);

	default List<PerguntaDto> toCustom(List<Pergunta> perguntas) {
		List<Pergunta> perguntasSemDuplicados = perguntas.stream().collect(Collectors.collectingAndThen(
				Collectors.toMap(Pergunta::getIdPergunta, d -> d, (d1, d2) -> d1), m -> new ArrayList<>(m.values())));
		
		List<PerguntaDto> dtos = perguntasSemDuplicados.stream().map(p -> {
			return PerguntaDto.builder().idPergunta(p.getIdPergunta()).descricao(p.getDescricao())
					.ordem(p.getOrdem()).obrigatoria(p.getObrigatoria()).tipoResposta(p.getTipoResposta())
					.respostas(RESPOSTA_MAPPER.to(p.getRespostas())).build();
		}).toList();
		
		return dtos.stream().sorted(Comparator.comparing(PerguntaDto::getOrdem)).toList();
	}
}
