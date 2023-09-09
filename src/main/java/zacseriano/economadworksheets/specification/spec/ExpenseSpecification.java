package zacseriano.economadworksheets.specification.spec;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import zacseriano.economadworksheets.domain.model.Expense;
import zacseriano.economadworksheets.domain.model.Origin;
import zacseriano.economadworksheets.domain.model.PaymentType;
import zacseriano.economadworksheets.specification.common.GenericSpecification;
import zacseriano.economadworksheets.specification.common.QueryOperator;

public class ExpenseSpecification extends GenericSpecification<Expense> {
	public static Specification<Expense> initialize(){
		return Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
	}
	
	public static Specification<Expense> paymentTypeName(String paymentTypeName) {
    	return (Root<Expense> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<Expense, PaymentType> expensePaymentTypeJoin = root.join("paymentType", JoinType.LEFT);
            query.distinct(true);
            return builder.equal(expensePaymentTypeJoin.get("name"), paymentTypeName);
        };
    }
	
	public static Specification<Expense> originName(String originName) {
		return (Root<Expense> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Join<Expense, Origin> expenseOrigin = root.join("origin", JoinType.LEFT);
            query.distinct(true);
            return builder.equal(expenseOrigin.get("name"), originName);
        };
	}
	
	public static Specification<Expense> initialDate(LocalDate initialDate) {
		return createSpecification(createFilter(QueryOperator.GREATER_THAN_OR_EQUAL, "date", initialDate));
	}
	
	public static Specification<Expense> finalDate(LocalDate finalDate) {
		return createSpecification(createFilter(QueryOperator.LESS_THAN_OR_EQUAL, "date", finalDate));
	}
}
