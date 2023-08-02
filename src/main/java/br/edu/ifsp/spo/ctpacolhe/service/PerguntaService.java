package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.entity.Pergunta;
import br.edu.ifsp.spo.ctpacolhe.repository.PerguntaRepository;

@Service
@Transactional
public class PerguntaService {
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	public List<Pergunta> buscaPerguntas() {
		List<Pergunta> perguntas = perguntaRepository.findAll();
		return perguntas;
	}
}
