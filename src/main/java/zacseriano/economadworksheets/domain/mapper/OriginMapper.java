package zacseriano.economadworksheets.domain.mapper;

import org.mapstruct.Mapper;

import zacseriano.economadworksheets.domain.dto.OriginDto;
import zacseriano.economadworksheets.domain.form.OriginForm;
import zacseriano.economadworksheets.domain.model.Origin;

@Mapper(componentModel = "spring")
public interface OriginMapper extends EntityMapper<OriginDto, Origin, OriginForm> {

}
