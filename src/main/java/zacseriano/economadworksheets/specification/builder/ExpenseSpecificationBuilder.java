package zacseriano.economadworksheets.specification.builder;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import zacseriano.economadworksheets.domain.model.Expense;
import zacseriano.economadworksheets.shared.utils.DateUtils;
import zacseriano.economadworksheets.specification.filter.ExpenseFilter;
import zacseriano.economadworksheets.specification.spec.ExpenseSpecification;

public class ExpenseSpecificationBuilder {
	public static Specification<Expense> builder(ExpenseFilter filter) {
		var specification = ExpenseSpecification.initialize();
		specification = deadlineMonthDescription(filter.getMonthDescription(), specification);
		specification = paymentTypeNameFilter(filter.getPaymentTypeName(), specification);
		specification = originNameFilter(filter.getOriginName(), specification);
		specification = initialDateFilter(filter.getInitialDate(), specification);
		specification = finalDateFilter(filter.getFinalDate(), specification);
		return specification;
	}
	
	private static Specification<Expense> deadlineMonthDescription(String monthDescription, Specification<Expense> specification) {
		if(monthDescription != null) {
			LocalDate deadlineMonth = DateUtils.monthDescriptionToDate(monthDescription);
			LocalDate initialDeadline = deadlineMonth.withDayOfMonth(1);
			LocalDate finalDeadline = deadlineMonth.withDayOfMonth(deadlineMonth.lengthOfMonth());
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
