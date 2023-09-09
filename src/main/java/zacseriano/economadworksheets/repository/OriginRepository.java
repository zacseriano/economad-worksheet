package zacseriano.economadworksheets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zacseriano.economadworksheets.domain.model.Origin;

public interface OriginRepository extends JpaRepository<Origin, Integer>{
	@Query("SELECT o FROM Origin o WHERE " +
		       "LOWER(FUNCTION('TRANSLATE', o.name, " +
		       "'áàãâäéèëêíìïîóòöôõúùüûç', " +
		       "'aaaaaeeeeiiiioooouuuuc')) " +
		       "LIKE %:name%")
	Optional<Origin> findByName(@Param("name") String name);
}
