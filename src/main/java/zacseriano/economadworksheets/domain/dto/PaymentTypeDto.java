package zacseriano.economadworksheets.domain.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zacseriano.economadworksheets.domain.enums.PaymentTypeEnum;

@Getter
@Setter
@NoArgsConstructor
public class PaymentTypeDto {
	private Integer id;
	private String nome;
	private LocalDate dataFatura;
	private PaymentTypeEnum tipoPagamentoEnum;
}
