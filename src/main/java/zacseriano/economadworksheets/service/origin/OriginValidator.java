package zacseriano.economadworksheets.service.origin;

import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import zacseriano.economadworksheets.domain.form.OriginForm;

@Component
@RequiredArgsConstructor
public class OriginValidator {
	public void validateForm (OriginForm form) {
		if (form.getName().isBlank()) {
			throw new ValidationException("Insert Origin's name");
		}
	}
}
