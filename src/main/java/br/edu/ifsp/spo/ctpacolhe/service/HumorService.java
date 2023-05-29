package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.exception.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.HumorRepository;

@Service
@Transactional
public class HumorService {
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private HumorRepository humorRepository;

	public Humor criaHumor(HumorCreateDto dto) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();

		if (humorRepository.existsByDataHumorAndIdUsuario(LocalDate.now(), usuarioAutenticado.getIdUsuario())) {
			throw new ValidationException(MensagemExceptionType.HUMOR_REGISTRADO_HOJE);
		}

		Humor humor = Humor.builder()
				.idHumor(UUID.randomUUID())
				.dataHumor(LocalDate.now())
				.idUsuario(usuarioAutenticado.getIdUsuario())
				.idSentimento(dto.getIdSentimento())
				.build();

		return humorRepository.save(humor);
	}

	public List<Humor> buscaHumores(LocalDate dataHumor) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();
		
		Humor humor = Humor.builder()
				.dataHumor(dataHumor)
				.idUsuario(usuarioAutenticado.getIdUsuario())
				.build();
		
		List<Humor> humores = humorRepository.findAll(Example.of(humor));
		
		return humores;
	}
}
