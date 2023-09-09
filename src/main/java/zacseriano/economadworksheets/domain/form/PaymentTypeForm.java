package zacseriano.economadworksheets.domain.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zacseriano.economadworksheets.domain.enums.PaymentTypeEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeForm {
	private String name;
	private PaymentTypeEnum tipoPagamentoEnum;
}
