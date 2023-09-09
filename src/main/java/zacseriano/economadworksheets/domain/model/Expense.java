package zacseriano.economadworksheets.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Expense extends BaseModel {
	private static BigDecimal salary = new BigDecimal(5500);
	private String description;
	private BigDecimal expenseValue;
	private LocalDate date;	
	private LocalDate deadline;
	private Integer installment;
	private Integer installmentsNumber;
	@ManyToOne(cascade = CascadeType.ALL)
	private Origin origin;
	@ManyToOne(cascade = CascadeType.ALL)
	private PaymentType paymentType;	
	
	public static BigDecimal getSalary() {
		return Expense.salary;
	}
	
	public static void setSalary(BigDecimal salary) {
		Expense.salary = salary;
	}
}
