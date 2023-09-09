package zacseriano.economadworksheets.domain.mapper;

import org.mapstruct.Mapper;

import zacseriano.economadworksheets.domain.dto.PaymentTypeDto;
import zacseriano.economadworksheets.domain.form.PaymentTypeForm;
import zacseriano.economadworksheets.domain.model.PaymentType;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper extends EntityMapper<PaymentTypeDto, PaymentType, PaymentTypeForm> {

}
