package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDate;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.filter.HumorFiltro;
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

	public Page<Humor> buscaHumores(HumorFiltro filtro, Pageable paginacao) {
		Usuario usuarioAutenticado = usuarioService.buscaUsuarioAutenticado();
		
		filtro.setIdUsuario(usuarioAutenticado.getIdUsuario());
		
		return humorRepository.findAll(filtro, paginacao);
	}
}
