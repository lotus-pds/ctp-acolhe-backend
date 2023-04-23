package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDate;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.SentimentoHumor;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.repository.HumorRepository;

@Service
@Transactional
public class HumorService {
	
	@Autowired
	private HumorRepository humorRepository;
	
	public Humor criaHumor(HumorCreateDto dto) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Humor humor = Humor.builder()
				.idHumor(UUID.randomUUID())
				.dataHumor(LocalDate.now())
				//.idUsuario(authentication.)
				//TODO Pegar id do usuário logado na sessão
				.build();
		return humorRepository.save(humor);
	}
}
