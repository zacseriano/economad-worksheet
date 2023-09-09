package zacseriano.economadworksheets.specification.builder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import zacseriano.economadworksheets.domain.model.Expense;
import zacseriano.economadworksheets.specification.filter.ExpenseFilter;
import zacseriano.economadworksheets.specification.spec.ExpenseSpecification;

public class ExpenseSpecificationBuilder {
	public static Specification<Expense> builder(ExpenseFilter filter) {
		var specification = ExpenseSpecification.naoDeletados();
		Map<Specification<Expense>, String> specsMap = new HashMap<Specification<Expense>, String>();

		paymentTypeNameFilter(filter.getPaymentTypeName(), specsMap);
		originNameFilter(filter.getOriginName(), specsMap);
		initialDateFilter(filter.getInitialDate(), specsMap);
		finalDateFilter(filter.getFinalDate(), specsMap);
		
		var specifications = specsMap.keySet();
		for (var spec : specifications) {
			var conditional = specsMap.get(spec);
			if (conditional.equals("and")) {
				specification = specification.and(spec);
			} else {
				specification = specification.or(spec);
			}
		}
		return specification;
	}

	private static void initialDateFilter(LocalDate initialDate, Map<Specification<Expense>, String> specs) {
		if(initialDate != null) {
			var spec = ExpenseSpecification.initialDate(initialDate);
			if (spec != null) {
				specs.put(spec, "and");
			}
		}		
	}

	private static void finalDateFilter(LocalDate finalDate, Map<Specification<Expense>, String> specs) {
		if(finalDate != null) {
			var spec = ExpenseSpecification.finalDate(finalDate);
			if (spec != null) {
				specs.put(spec, "and");
			}
		}
	}

	private static void originNameFilter(String originName, Map<Specification<Expense>, String> specs) {
		if (originName != null) {
			var spec = ExpenseSpecification.originName(originName);
			if (spec != null) {
				specs.put(spec, "and");
			}
		}
		
	}

	private static void paymentTypeNameFilter(String paymentTypeName, Map<Specification<Expense>, String> specs) {
		if (paymentTypeName != null) {
			var spec = ExpenseSpecification.paymentTypeName(paymentTypeName);
			if (spec != null) {
				specs.put(spec, "and");
			}
		}
	}

}
