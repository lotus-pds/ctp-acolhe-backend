package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;

public interface UsuarioRepositoryCustom {
	Page<Usuario> findAll(FiltroWrapper filtroWrapper);
	void removePerfisByIds(List<UUID> idsUsuarios);
	void removeHumoresByIds(List<UUID> idsUsuarios);
}
