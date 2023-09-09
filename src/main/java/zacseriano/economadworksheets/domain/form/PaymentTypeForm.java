package zacseriano.economadworksheets.domain.form;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zacseriano.economadworksheets.domain.enums.PaymentTypeEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTypeForm {
	@NotNull
	private String name;
	@NotNull
	private PaymentTypeEnum paymentTypeEnum;
	@NotNull
	private Integer billingDate;
}
