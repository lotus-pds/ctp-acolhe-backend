package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.repository.custom.IncidenteRepositoryCustom;

public interface IncidenteRepository extends JpaRepository<Incidente, UUID>, IncidenteRepositoryCustom {
	@Query("SELECT i FROM Incidente i LEFT JOIN FETCH i.usuarioCopia LEFT JOIN FETCH i.status"
			+ " LEFT JOIN FETCH i.detalhes WHERE i.idIncidente = ?1")
	Optional<Incidente> findById(UUID idIncidente);
}
