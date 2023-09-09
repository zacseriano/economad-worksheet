package zacseriano.economadworksheets.service.paymentType;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import zacseriano.economadworksheets.domain.form.PaymentTypeForm;

@Component
@RequiredArgsConstructor
public class PaymentTypeValidator {
	@Autowired
	private Validator validator;
	public void validateForm(PaymentTypeForm form) {
		Set<ConstraintViolation<PaymentTypeForm>> violations = validator.validate(form);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
	}
}
