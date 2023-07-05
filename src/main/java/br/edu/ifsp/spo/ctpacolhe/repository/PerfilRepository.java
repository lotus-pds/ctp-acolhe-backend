package br.edu.ifsp.spo.ctpacolhe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, String>  {
	boolean existsByIdPerfil(String idPerfil);
}
