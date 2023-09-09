package zacseriano.economadworksheets.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zacseriano.economadworksheets.domain.enums.PaymentTypeEnum;

@Getter
@Setter
@NoArgsConstructor
public class PaymentTypeDto {
	private Integer id;
	private String name;
	private Integer billingDate;
	private PaymentTypeEnum paymentTypeEnum;
}
