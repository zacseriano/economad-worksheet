package zacseriano.economadworksheets.domain.form;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zacseriano.economadworksheets.shared.validator.data.StringAsLocalDateValid;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseForm {
	@NotBlank
	private String description;
	private BigDecimal value;
	@StringAsLocalDateValid
	@NotNull
	private String date;
	@StringAsLocalDateValid
	private String deadline;
	private Integer installmentNumber;
	private String originName;
	private String paymentTypeName;
}
