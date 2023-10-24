package tcc.job.devs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.UserLanguageEntity;
import tcc.job.devs.entities.UserLanguageSkillEntity;
import tcc.job.devs.payloads.UserLanguagePayloads;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserLanguageMapper {

    UserLanguageMapper INSTANCE = Mappers.getMapper(UserLanguageMapper.class);

    @Mapping(target = "languageSkills.languageId", expression = "java(mapLanguageId(entity.getLanguageSkills()))")
    UserLanguagePayloads.UserLanguageModel toModel(UserLanguageEntity entity);

    @Mapping(target = "languageSkills", ignore = true)
    UserLanguageEntity toEntity(UserLanguagePayloads.CreateUserLanguagePayload createUserPayload);

    @Mapping(target = "languageSkills", ignore = true)
    UserLanguageEntity toEntity(UserLanguagePayloads.UpdateUserLanguagePayload updateUserPayload);

    @Mapping(target = "languageSkills", ignore = true)
    UserLanguageEntity toEntity(UserLanguagePayloads.UserLanguageModel model);

    void updateEntityFromPayload(UserLanguagePayloads.UserLanguageModel model, @MappingTarget UserLanguageEntity entity);

    void updateModelFromEntity(UserLanguageEntity entity, @MappingTarget UserLanguagePayloads.UserLanguageModel model);

    default int mapLanguageId(Set<UserLanguageSkillEntity> languageSkills) {
        return languageSkills.stream()
                .findFirst()
                .map(userLanguageSkillEntity -> userLanguageSkillEntity.getLanguage().getId())
                .orElse(0);  // ou outro valor padrão ou exceção
    }

}
