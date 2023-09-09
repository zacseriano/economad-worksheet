package zacseriano.economadworksheets.service.paymentType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import zacseriano.economadworksheets.domain.form.PaymentTypeForm;
import zacseriano.economadworksheets.domain.mapper.PaymentTypeMapper;
import zacseriano.economadworksheets.domain.model.PaymentType;
import zacseriano.economadworksheets.repository.PaymentTypeRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentTypeService {
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;
	@Autowired
	private PaymentTypeMapper paymentTypeMapper;
	
	public PaymentType create(PaymentTypeForm form) {
		PaymentType paymentType = paymentTypeMapper.toModel(form);
		paymentType = paymentTypeRepository.save(paymentType);		
		return paymentType;
	}
	
	public PaymentType findOrCreate(PaymentTypeForm form) {
		PaymentType paymentType = paymentTypeRepository.findByName(form.getName());		
		if (paymentType == null) {
			paymentType = create(form);		
		}		
		return paymentType;
	}

	public PaymentType findByName(String paymentTypeName) {
		PaymentType paymentType = paymentTypeRepository.findByName(paymentTypeName);		
		if (paymentType == null) {
			throw new ValidationException(String.format("Payment Type with name %s not found.", paymentTypeName));		
		}		
		return paymentType;
	}
}
