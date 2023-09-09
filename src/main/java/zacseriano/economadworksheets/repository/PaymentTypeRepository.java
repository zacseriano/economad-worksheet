package zacseriano.economadworksheets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zacseriano.economadworksheets.domain.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer>{
	@Query("SELECT p FROM PaymentType p WHERE " +
		       "LOWER(FUNCTION('TRANSLATE', p.name, " +
		       "'áàãâäéèëêíìïîóòöôõúùüûç', " +
		       "'aaaaaeeeeiiiioooouuuuc')) " +
		       "LIKE %:name%")
	Optional<PaymentType> findByName(@Param("name") String name);

}
