package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDate;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.HumorRepository;

@Service
@Transactional
public class HumorService {

	@Autowired
	private HumorRepository humorRepository;

	public Humor criaHumor(HumorCreateDto dto) {
		Usuario usuarioAutenticado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (humorRepository.existsByDataHumorAndIdUsuario(LocalDate.now(), usuarioAutenticado.getIdUsuario())) {
			throw new ValidationException("Humor já registrado hoje");
		}

		Humor humor = Humor.builder()
				.idHumor(UUID.randomUUID())
				.dataHumor(LocalDate.now())
				.idUsuario(usuarioAutenticado.getIdUsuario())
				.idSentimento(dto.getIdSentimento())
				.build();

		return humorRepository.save(humor);
	}
}
