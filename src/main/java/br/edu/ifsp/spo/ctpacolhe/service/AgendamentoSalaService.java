package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		//TODO: adicionar validações necessárias

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
	
}
