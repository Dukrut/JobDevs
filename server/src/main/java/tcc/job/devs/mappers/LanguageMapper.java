package tcc.job.devs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.LanguageEntity;
import tcc.job.devs.payloads.LanguagePayloads;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    LanguagePayloads.LanguagePayload toModel(LanguageEntity entity);

}
