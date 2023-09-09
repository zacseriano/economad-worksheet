package zacseriano.economadworksheets.domain.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import zacseriano.economadworksheets.domain.enums.PaymentTypeEnum;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentType extends BaseModel {
	@Column(unique = true)
	private String name;
	@Enumerated(EnumType.STRING)
	private PaymentTypeEnum paymentTypeEnum;
	private LocalDate bilingDate;
	@OneToMany(mappedBy = "paymentType", cascade = CascadeType.ALL)
	private List<Expense> expenses;
}
