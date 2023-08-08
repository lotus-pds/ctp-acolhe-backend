package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.AgendamentoSala;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.AgendamentoSalaRepository;

@Service
@Transactional
public class AgendamentoSalaService {
	
	@Autowired
	private AgendamentoSalaRepository agendamentoSalaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public AgendamentoSala criaAgendamento(AgendamentoSalaCreateDto agendamentoDto) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();
		
		validaPeriodoAgendamento(agendamentoDto.getDataAtendimentoInicial(), agendamentoDto.getDataAtendimentoFinal());

		AgendamentoSala agendamento = AgendamentoSala.builder()
				.idAgendamento(UUID.randomUUID())
				.nomeAlunos(agendamentoDto.getNomeAlunos())
				.nomeTecnico(agendamentoDto.getNomeTecnico())
				.dataAtendimentoInicial(agendamentoDto.getDataAtendimentoInicial())
				.dataAtendimentoFinal(agendamentoDto.getDataAtendimentoFinal())
				.idCriadoPor(usuarioAutenticado.getIdUsuario())
				.dataCriacao(LocalDateTime.now())
				.build();
		
		return agendamentoSalaRepository.save(agendamento);
	}
	
	public AgendamentoSala buscaAgendamento(UUID idAgendamento) {
		return validaAgendamento(idAgendamento);
	}
	
	public Page<AgendamentoSala> buscaAgendamentos(FiltroWrapper filtroWrapper) {
		Page<AgendamentoSala> agendamentos = agendamentoSalaRepository.findAll(filtroWrapper);
		return agendamentos;
	}
	
	public AgendamentoSala atualizaAgendamento(UUID idAgendamento, AgendamentoSalaCreateDto agendamentoDto) {
		AgendamentoSala agendamento = validaAgendamento(idAgendamento);
		
		validaPeriodoAgendamento(agendamentoDto.getDataAtendimentoInicial(), agendamentoDto.getDataAtendimentoFinal());
		
		agendamento.setNomeAlunos(agendamentoDto.getNomeAlunos());
		agendamento.setNomeTecnico(agendamentoDto.getNomeTecnico());
		agendamento.setDataAtendimentoInicial(agendamentoDto.getDataAtendimentoInicial());
		agendamento.setDataAtendimentoFinal(agendamentoDto.getDataAtendimentoFinal());
		
		return agendamentoSalaRepository.save(agendamento);
	}
	
	public void deletaAgendamento(UUID idAgendamento) {
		AgendamentoSala agendamento = validaAgendamento(idAgendamento);
		
		agendamentoSalaRepository.delete(agendamento);
	}
	
	private AgendamentoSala validaAgendamento(UUID idAgendamento) {
		return agendamentoSalaRepository.findById(idAgendamento)
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.AGENDAMENTO_SALA_NAO_ENCONTRADO));
	}

	private void validaPeriodoAgendamento(LocalDateTime atendimentoInicial, LocalDateTime atendimentoFinal) {		
		if (atendimentoInicial.isAfter(atendimentoFinal)) {
			throw new ValidationException(MensagemExceptionType.AGENDAMENTO_INICIAL_DEPOIS_DE_AGENDAMENTO_FINAL);
		}
		
		if (atendimentoInicial.isBefore(LocalDateTime.now()) ||
				atendimentoFinal.isBefore(LocalDateTime.now())) {
			throw new ValidationException(MensagemExceptionType.PERIODO_AGENDAMENTO_NO_PASSADO);
		}
		
		if (!agendamentoSalaRepository.findAllByPeriod(atendimentoInicial, atendimentoFinal).isEmpty()) {
			throw new ValidationException(MensagemExceptionType.EXISTE_AGENDAMENTO_NESTE_PERIODO);
		}
	}
	
}
