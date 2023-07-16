package br.edu.ifsp.spo.ctpacolhe.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.dto.PerguntaIncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.dto.RespostaIncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.entity.IncidenteDetalhe;
import br.edu.ifsp.spo.ctpacolhe.entity.Resposta;

@Mapper(componentModel = "spring")
public interface IncidenteMapper {
	
	UsuarioCopiaMapper USUARIO_COPIA_MAPPER = Mappers.getMapper(UsuarioCopiaMapper.class);
	StatusMapper STATUS_MAPPER = Mappers.getMapper(StatusMapper.class);
	
	default IncidenteDto toCustom(Incidente incidente) {
		Set<IncidenteDetalhe> detalhes = incidente.getDetalhes();
		
		List<IncidenteDetalhe> perguntasSemDuplicados = detalhes.stream().collect(Collectors.collectingAndThen(
				Collectors.toMap(IncidenteDetalhe::getPergunta, d -> d, (d1, d2) -> d1), m -> new ArrayList<>(m.values())));
		
		List<PerguntaIncidenteDto> perguntasComRespostas = perguntasSemDuplicados.stream().map(deta -> {
			String perguntaAtual = deta.getPergunta();
			Set<Resposta> respostasDaPergunta = getRespostasByPergunta(detalhes, perguntaAtual);

			return PerguntaIncidenteDto.builder().descricao(perguntaAtual).ordem(deta.getOrdemPergunta())
					.tipoResposta(deta.getTipoResposta()).respostas(respostasDaPergunta.stream()
							.map(resp -> RespostaIncidenteDto.builder().descricao(resp.getDescricao()).ordem(resp.getOrdem()).build())
							.toList())
					.build();
		}).toList();

		return IncidenteDto.builder().assunto(incidente.getAssunto()).dataIncidente(incidente.getDataIncidente())
				.idIncidente(incidente.getIdIncidente()).perguntas(perguntasComRespostas)
				.usuarioCopia(USUARIO_COPIA_MAPPER.to(incidente.getUsuarioCopia()))
				.status(STATUS_MAPPER.to(incidente.getStatus())).build();
	}

	default Set<Resposta> getRespostasByPergunta(Set<IncidenteDetalhe> detalhes, String pergunta) {
		Set<Resposta> respostasDaPergunta = detalhes.stream().filter(deta -> deta.getPergunta().equals(pergunta))
				.map(deta -> {
					return Resposta.builder().descricao(deta.getResposta()).ordem(deta.getOrdemResposta()).build();
				}).collect(Collectors.toSet());

		return respostasDaPergunta;
	}

	default List<IncidenteDto> toCustom(List<Incidente> incidentes) {
		if(incidentes == null) {
			return new ArrayList<>();
		}
		
		return incidentes.stream().map(this::toCustom).toList();
	}
}
