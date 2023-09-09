package zacseriano.economadworksheets.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Origin extends BaseModel {
	@Column(unique = true)
	private String name;
	@OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
	private List<Expense> expenses;
}
