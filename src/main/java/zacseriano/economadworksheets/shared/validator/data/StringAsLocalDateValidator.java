package zacseriano.economadworksheets.shared.validator.data;

import java.time.format.DateTimeParseException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import zacseriano.economadworksheets.shared.utils.DateUtils;

public class StringAsLocalDateValidator implements ConstraintValidator<StringAsLocalDateValid, String> {
    @SuppressWarnings("unused")
	private String value;

    @Override
    public void initialize(StringAsLocalDateValid constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {    	
        if(value != null && !value.isBlank()) {
            try {
                DateUtils.stringToLocalDate(value);
            } catch (DateTimeParseException e) {
                return false;
            }
        }

        return true;
    }
}
