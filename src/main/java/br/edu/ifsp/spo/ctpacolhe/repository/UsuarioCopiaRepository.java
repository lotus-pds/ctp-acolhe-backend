package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.UsuarioCopia;

public interface UsuarioCopiaRepository extends JpaRepository<UsuarioCopia, UUID> {

}
