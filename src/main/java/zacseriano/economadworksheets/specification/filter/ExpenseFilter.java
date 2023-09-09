package zacseriano.economadworksheets.specification.filter;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseFilter {
	private String paymentTypeName; 
	private String originName;
	private Integer month;
	private Integer year;
	private LocalDate initialDate;
	private LocalDate finalDate;
}
