package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.repository.custom.IncidenteRepositoryCustom;

public interface IncidenteRepository extends JpaRepository<Incidente, UUID>, IncidenteRepositoryCustom {

}
