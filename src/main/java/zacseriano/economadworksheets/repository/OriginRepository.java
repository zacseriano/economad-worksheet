package zacseriano.economadworksheets.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import zacseriano.economadworksheets.domain.model.Origin;

public interface OriginRepository extends JpaRepository<Origin, UUID>{
	Origin findByName(String name);
}
