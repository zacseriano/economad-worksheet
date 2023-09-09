package zacseriano.economadworksheets.specification.common;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@SuppressWarnings("unchecked")
public class GenericSpecification<T> {

    static final String likeOperatorCharacter = "%";

    public static Filter builderFilter(QueryOperator operator, String key, Object value) {
        return Filter.builder()
                .operator(operator)
                .key(key)
                .value(value)
                .build();
    }

    public static <T> Specification<T> verifySpecification(Specification<T> actual, Specification<T> newSpec, String condition) {
        if(actual != null) {
            if(condition.equals("and")) {
                return actual.and(newSpec);
            } else {
                return actual.or(newSpec);
            }
        } else {
            return newSpec;
        }
    }

    protected static <T> Specification<T> createSpecification(Filter filtro) {
        switch (filtro.getOperator()) {
            case IN:
                return in(filtro.getKey(), filtro.getValues());
            case EQUAL:
                return equals(filtro.getKey(), filtro.getValue());
            case NOT_EQUAL:
                return notEquals(filtro.getKey(), filtro.getValue());
            case LESS_THAN:
                return lessThan(filtro.getKey(), filtro.getValue());
            case GREATER_THAN:
                return greaterThan(filtro.getKey(), filtro.getValue());
            case LESS_THAN_OR_EQUAL:
                return lessThanOrEqualTo(filtro.getKey(), filtro.getValue());
            case GREATER_THAN_OR_EQUAL:
                return greaterThanOrEqualTo(filtro.getKey(), filtro.getValue());
            case LIKE:
                return like(filtro.getKey(), filtro.getValue());
            case IS_NULL:
                return isNull(filtro.getKey());
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    private static <T> Specification<T> like(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.like( builder.lower(root.get(key)), likeOperatorCharacter+value.toString().toLowerCase(Locale.ROOT)+likeOperatorCharacter);
        };
    }

    private static <T> Specification<T> isNull(String key) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.isNull(root.get(key));
        };
    }

    private static <T> Specification<T> equals(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.equal(root.get(key), value);
        };
    }

    private static <T> Specification<T> notEquals(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.notEqual(root.get(key), value);
        };
    }

    private static <T> Specification<T> in(String key, Collection<Object> values) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.in(root.get(key)).value(values);
        };
    }

	private static <T> Specification<T> greaterThan(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if(value instanceof LocalDate) {
                return builder.greaterThan(root.get(key), (LocalDate) value);
            }

            return ((Specification<T>) gt(key, value)).toPredicate(root, query, builder);
        };
    }

    public static <T> Specification<T> greaterThanOrEqualTo(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return ((Specification<T>) greaterThan(key, value).or(equals(key, value))).toPredicate(root, query, builder);
        };
    }

    private static <T> Specification<T> lessThan(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if(value instanceof LocalDate) {
                return builder.lessThan(root.get(key), (LocalDate) value);
            }

            return ((Specification<T>) lt(key, value)).toPredicate(root, query, builder);
        };
    }

    private static <T> Specification<T> lessThanOrEqualTo(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return ((Specification<T>) lessThan(key, value).or(equals(key, value))).toPredicate(root, query, builder);
        };
    }

    private static <T> Specification<T> gt(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.gt(root.get(key), (Number) value);
        };
    }

    private static <T> Specification<T> lt(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.lt(root.get(key), (Number) value);
        };
    }

    protected static <T> Specification<T> startsWith(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return builder.like( builder.lower(root.get(key).as(String.class)), value.toString().toLowerCase(Locale.ROOT)+"%");
        };
    }

    protected static <T> Specification<T> startsWithCpfCnpj(String key, Object value) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            String val = value.toString().replace(".", "").replace("-", "").replace("/", "").toLowerCase(Locale.ROOT);
            var replacePonto = builder.function("replace", String.class, builder.lower(root.get(key).as(String.class)), builder.literal("."),
                    builder.literal(""));
            var replaceTraco = builder.function("replace", String.class, replacePonto, builder.literal("-"),
                    builder.literal(""));
            var replaceBarra = builder.function("replace", String.class, replaceTraco, builder.literal("/"),
                    builder.literal(""));

            return builder.like(replaceBarra,val+"%");
        };
    }

    protected static Filter criarFilterNulo(String key) {
        return Filter.builder()
                .operator(QueryOperator.IS_NULL)
                .key(key)
                .build();
    }

    protected static Filter createFilter(QueryOperator operator, String key, Object value) {
        return Filter.builder()
                .operator(operator)
                .key(key)
                .value(value)
                .build();
    }

    protected static Filter criarFilterValues(String key, List<Object> values) {
        return Filter.builder()
                .operator(QueryOperator.IN)
                .key(key)
                .values(values)
                .build();
    }
}

