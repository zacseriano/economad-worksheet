package zacseriano.economadworksheets.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import zacseriano.economadworksheets.domain.dto.ExpenseDto;
import zacseriano.economadworksheets.domain.form.ExpenseForm;
import zacseriano.economadworksheets.domain.model.Expense;
import zacseriano.economadworksheets.shared.utils.DateUtils;

@Mapper(componentModel = "spring")
public interface ExpenseMapper extends EntityMapper<ExpenseDto, Expense, ExpenseForm> {
    
    @Mappings({
    	@Mapping(target = "date", source = "date", dateFormat = DateUtils.DEFAULT_FORMAT),
        @Mapping(target = "deadline", source = "deadline", dateFormat = DateUtils.DEFAULT_FORMAT),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "origin", ignore = true),
        @Mapping(target = "paymentType", ignore = true),
        @Mapping(target = "installment", ignore = true),
    })
    Expense toModel(ExpenseForm form);

	@Mappings({ 
		@Mapping(target ="origin" , source = "origin.name"),
		@Mapping(target ="paymentType" , source = "paymentType.name")
	})
	ExpenseDto toDto(Expense entity);
}
