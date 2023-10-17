package tcc.job.devs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.UserLanguageProficiencyEntity;
import tcc.job.devs.payloads.LanguagePayloads;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserLanguageProficiencyMapper {

    UserLanguageProficiencyMapper INSTANCE = Mappers.getMapper(UserLanguageProficiencyMapper.class);

    @Mapping(target = "languageId", source = "entity.language.id")
    LanguagePayloads.UserLanguageProficiencyModel toModel(UserLanguageProficiencyEntity entity);

}
