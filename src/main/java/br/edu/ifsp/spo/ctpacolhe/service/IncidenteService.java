package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoResposta;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.PerguntaCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Curso;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.entity.IncidenteDetalhe;
import br.edu.ifsp.spo.ctpacolhe.entity.IncidenteDetalhe.IncidenteDetalheBuilder;
import br.edu.ifsp.spo.ctpacolhe.entity.Pergunta;
import br.edu.ifsp.spo.ctpacolhe.entity.Resposta;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.UsuarioCopia;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.IncidenteFiltro;
import br.edu.ifsp.spo.ctpacolhe.repository.IncidenteRepository;
import br.edu.ifsp.spo.ctpacolhe.repository.PerguntaRepository;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioCopiaRepository;

@Service
@Transactional
public class IncidenteService {
	
	@Autowired
	private IncidenteRepository incidenteRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private UsuarioCopiaRepository usuarioCopiaRepository;
	
	private static final String PENDENTE = "PEN";
	private static final String CANCELADO = "CAN";
	private static final String EM_PROCESSO = "EPR";
	private static final String FINALIZADO = "FIN";
	
	public Page<Incidente> buscaIncidentesAutenticado(FiltroWrapper filtroWrapper) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();
		
		IncidenteFiltro filtro = (IncidenteFiltro) filtroWrapper.getFiltro();
		filtro.setIdUsuarioOrigem(usuarioAutenticado.getIdUsuario());
		
		return incidenteRepository.findAllBy(filtroWrapper);
	}
	
	public Page<Incidente> buscaIncidentes(FiltroWrapper filtroWrapper) {
		Page<Incidente> incidentes = incidenteRepository.findAllBy(filtroWrapper);
		return incidentes;
	}

	public Incidente criaIncidente(IncidenteCreateDto incidenteDto) {
		List<Pergunta> perguntasValidas = perguntaRepository.findAll();
		Set<IncidenteDetalhe> detalhes = validaPerguntas(incidenteDto, perguntasValidas);

		UsuarioCopia usuarioCopia = montaUsuarioCopia(usuarioService.validaUsuarioAutenticado());
		usuarioCopia = usuarioCopiaRepository.save(usuarioCopia);

		UUID idIncidente = UUID.randomUUID();
		detalhes.forEach(d -> d.setIdIncidente(idIncidente));

		Incidente incidente = Incidente.builder().idIncidente(idIncidente).idUsuarioCopia(usuarioCopia.getIdUsuarioCopia())
				.dataIncidente(LocalDateTime.now()).assunto(incidenteDto.getAssunto()).idStatus("PEN")
				.detalhes(detalhes).build();
		
		return incidenteRepository.save(incidente);
	}
	
	public Incidente buscaIncidente(UUID idIncidente) {
		Incidente incidente = incidenteRepository.findById(idIncidente)
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.INCIDENTE_NAO_ENCONTRADO));
		
		return incidente;
	}
	
	public Incidente cancelaIncidente(UUID idIncidente) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();
		Incidente incidente = buscaIncidente(idIncidente);
		
		if (!usuarioAutenticado.getIdUsuario().equals(incidente.getIdUsuarioOrigem())) {
			throw new ValidationException(MensagemExceptionType.INCIDENTE_DE_OUTRO_ALUNO);
		}
		
		if (CANCELADO.equals(incidente.getIdStatus()) || FINALIZADO.equals(incidente.getIdStatus())) {
			throw new ValidationException(MensagemExceptionType.INCIDENTE_JA_CANCELADO_OU_FINALIZADO);
		}
		
		incidente.setIdStatus(CANCELADO);
		
		return incidenteRepository.save(incidente);
	}
	
	public Incidente processaIncidente(UUID idIncidente) {
		Incidente incidente = buscaIncidente(idIncidente);
		
		if (EM_PROCESSO.equals(incidente.getIdStatus()) || CANCELADO.equals(incidente.getIdStatus())
				|| FINALIZADO.equals(incidente.getIdStatus())) {
			throw new ValidationException(MensagemExceptionType.INCIDENTE_EM_PROCESSO_CANCELADO_OU_FINALIZADO);
		}
		
		incidente.setIdStatus(EM_PROCESSO);
		
		return incidenteRepository.save(incidente);
	}
	
	public Incidente finalizarIncidente(UUID idIncidente) {
		Incidente incidente = buscaIncidente(idIncidente);
		
		if (PENDENTE.equals(incidente.getIdStatus()) || CANCELADO.equals(incidente.getIdStatus())
				|| FINALIZADO.equals(incidente.getIdStatus())) {
			throw new ValidationException(MensagemExceptionType.INCIDENTE_PENDENTE_CANCELADO_OU_FINALIZADO);
		}
		
		incidente.setIdStatus(FINALIZADO);
		
		return incidenteRepository.save(incidente);
	}

	private Set<IncidenteDetalhe> validaPerguntas(IncidenteCreateDto incidenteDto, List<Pergunta> perguntas) {
		Set<IncidenteDetalhe> detalhes = new HashSet<>();
				
		List<PerguntaCreateDto> perguntasIncidente = Optional.ofNullable(incidenteDto.getPerguntas())
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.INCIDENTE_NAO_POSSUI_PERGUNTAS));
		
		perguntasIncidente.forEach(pi -> {
			Pergunta perguntaValida = perguntas.stream().filter(p -> p.getIdPergunta().equals(pi.getIdPergunta())).findFirst()
					.orElseThrow(() -> new ValidationException(MensagemExceptionType.PERGUNTA_NAO_ENCONTRADA,
							pi.getIdPergunta()));
			
			if (!TipoResposta.MULTISELECT.equals(perguntaValida.getTipoResposta()) && pi.getRespostas().size() > 1) {
				throw new ValidationException(MensagemExceptionType.PERGUNTA_NAO_PERMITE_MULTIPLAS_RESPOSTAS,
						perguntaValida.getIdPergunta());
			}
			
			detalhes.addAll(validaRespostas(pi, perguntaValida));
		});
		
		return detalhes;
	}

	private Set<IncidenteDetalhe> validaRespostas(PerguntaCreateDto perguntaIncidente, Pergunta pergunta) {
		return perguntaIncidente.getRespostas().stream().map(ri -> {
			IncidenteDetalheBuilder incidenteDetalheBuilder = IncidenteDetalhe.builder().idIncidenteDetalhe(UUID.randomUUID()).pergunta(pergunta.getDescricao())
					 .ordemPergunta(pergunta.getOrdem()).tipoResposta(pergunta.getTipoResposta());
			
			if(TipoResposta.DISSERTATIVA.equals(pergunta.getTipoResposta())) {
				incidenteDetalheBuilder.resposta(ri.getTextoResposta()).ordemResposta(1);
			} else {
				Resposta resposta = pergunta.getRespostas().stream().filter(r -> r.getIdResposta().equals(ri.getIdResposta())).findFirst()
						.orElseThrow(() -> new ValidationException(MensagemExceptionType.RESPOSTA_PARA_PERGUNTA_NAO_ENCONTRADA,
								 ri.getIdResposta(), pergunta.getIdPergunta()));
				
				incidenteDetalheBuilder.resposta(resposta.getDescricao()).ordemResposta(resposta.getOrdem());
			}
			
			return incidenteDetalheBuilder.build();
		}).collect(Collectors.toSet());
	}
	
	private UsuarioCopia montaUsuarioCopia(Usuario usuarioAutenticado) {
		Curso curso = Optional.ofNullable(usuarioAutenticado.getCurso()).orElseGet(Curso::new);
		String tipo = curso.getTipo() != null ? curso.getTipo().toString() : null;

		return UsuarioCopia.builder().idUsuarioCopia(UUID.randomUUID())
				.idUsuarioOrigem(usuarioAutenticado.getIdUsuario()).nome(usuarioAutenticado.getNome())
				.email(usuarioAutenticado.getEmail()).telefone(usuarioAutenticado.getTelefone())
				.nomeCurso(curso.getNome()).tipoCurso(tipo).periodo(usuarioAutenticado.getPeriodo())
				.turma(usuarioAutenticado.getTurma()).prontuario(usuarioAutenticado.getProntuario())
				.urlAvatar(usuarioAutenticado.getUrlAvatar()).build();
	}
}
