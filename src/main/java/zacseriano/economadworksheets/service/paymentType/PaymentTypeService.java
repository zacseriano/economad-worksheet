package zacseriano.economadworksheets.service.paymentType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import zacseriano.economadworksheets.domain.dto.PaymentTypeDto;
import zacseriano.economadworksheets.domain.form.PaymentTypeForm;
import zacseriano.economadworksheets.domain.mapper.PaymentTypeMapper;
import zacseriano.economadworksheets.domain.model.PaymentType;
import zacseriano.economadworksheets.repository.PaymentTypeRepository;
import zacseriano.economadworksheets.shared.utils.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentTypeService {
	@Autowired
	private PaymentTypeRepository repository;
	@Autowired
	private PaymentTypeMapper mapper;
	
	public List<PaymentTypeDto> listAll(){
		return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}
	
	public PaymentType create(PaymentTypeForm form) {
		PaymentType paymentType = mapper.toModel(form);
		paymentType = repository.saveAndFlush(paymentType);		
		return paymentType;
	}

	public PaymentType findByName(String paymentTypeName) {
		String normalizedName = StringUtils.normalize(paymentTypeName);
		Optional<PaymentType> paymentType = repository.findByName(normalizedName);
		if (paymentType.isEmpty()) {
			throw new ValidationException(String.format("Payment Type with name %s not found.", paymentTypeName));		
		}		
		return paymentType.get();
	}
}
