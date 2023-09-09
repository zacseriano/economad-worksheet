package zacseriano.economadworksheets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import zacseriano.economadworksheets.domain.model.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer>{
	PaymentType findByName(String name);
}
