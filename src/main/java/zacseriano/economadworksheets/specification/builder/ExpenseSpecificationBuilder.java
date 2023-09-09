package zacseriano.economadworksheets.specification.builder;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import zacseriano.economadworksheets.domain.model.Expense;
import zacseriano.economadworksheets.specification.filter.ExpenseFilter;
import zacseriano.economadworksheets.specification.spec.ExpenseSpecification;

public class ExpenseSpecificationBuilder {
	public static Specification<Expense> builder(ExpenseFilter filter) {
		var specification = ExpenseSpecification.initialize();
		specification = deadlineMonth(filter.getMonth(), filter.getYear(), specification);
		specification = paymentTypeNameFilter(filter.getPaymentTypeName(), specification);
		specification = originNameFilter(filter.getOriginName(), specification);
		specification = initialDateFilter(filter.getInitialDate(), specification);
		specification = finalDateFilter(filter.getFinalDate(), specification);
		return specification;
	}
	
	private static Specification<Expense> deadlineMonth(Integer month, Integer year, Specification<Expense> specification) {
		if(month != null && year != null) {
			LocalDate initialDeadline = LocalDate.of(year, month, 1);
			LocalDate finalDeadline = initialDeadline.withDayOfMonth(initialDeadline.lengthOfMonth());
			specification = ExpenseSpecification.verifySpecification(specification, ExpenseSpecification.initialDeadline(initialDeadline), "and");
			return ExpenseSpecification.verifySpecification(specification, ExpenseSpecification.finalDeadline(finalDeadline), "and");
		}
		return specification;
	}

	private static Specification<Expense> initialDateFilter(LocalDate initialDate, Specification<Expense> specification) {
		if(initialDate != null) {
			return ExpenseSpecification.verifySpecification(specification, ExpenseSpecification.initialDate(initialDate), "and");
		}
		return specification;
	}

	private static Specification<Expense> finalDateFilter(LocalDate finalDate, Specification<Expense> specification) {
		if(finalDate != null) {
			return ExpenseSpecification.verifySpecification(specification, ExpenseSpecification.finalDate(finalDate), "and");
		}
		return specification;
	}

	private static Specification<Expense> originNameFilter(String originName, Specification<Expense> specification) {
		if (originName != null) {
			return ExpenseSpecification.verifySpecification(specification, ExpenseSpecification.originName(originName), "and");
		}
		return specification;
	}

	private static Specification<Expense> paymentTypeNameFilter(String paymentTypeName, Specification<Expense> specification) {
		if (paymentTypeName != null) {
			return ExpenseSpecification.verifySpecification(specification, ExpenseSpecification.paymentTypeName(paymentTypeName), "and");
		}
		return specification;
	}

}
