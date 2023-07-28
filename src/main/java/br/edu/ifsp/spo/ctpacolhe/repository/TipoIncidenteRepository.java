package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente;

public interface TipoIncidenteRepository extends JpaRepository<TipoIncidente, UUID> {

}
