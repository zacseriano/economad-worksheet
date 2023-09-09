package zacseriano.economadworksheets.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseDto {
	private Integer id;
	private String description;
	private BigDecimal expenseValue;
	private LocalDate date;	
	private LocalDate deadline;
	private String installment;
	private String origin;
	private String paymentType;
}
