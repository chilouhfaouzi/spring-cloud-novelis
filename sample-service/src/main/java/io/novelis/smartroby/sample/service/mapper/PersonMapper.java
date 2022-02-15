package io.novelis.smartroby.sample.service.mapper;

import io.novelis.smartroby.sample.domain.PersonEntity;
import io.novelis.smartroby.sample.service.dto.PersonDto;
import io.novelis.smartroby.sample.service.dto.PersonPartialDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper extends EntityMapper<PersonDto, PersonEntity> {
    void updateEntityFromPartialDto(PersonPartialDto personPartialDto, @MappingTarget PersonEntity personEntity);
}
