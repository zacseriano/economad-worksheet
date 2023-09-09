package zacseriano.economadworksheets.specification.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter {
    private String key;
    private Object value;
    private List<Object> values;
    private QueryOperator operator;
}
