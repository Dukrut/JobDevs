package tcc.job.devs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.entities.UserLanguageEntity;
import tcc.job.devs.entities.UserLanguageSkillEntity;
import tcc.job.devs.payloads.UserLanguagePayloads;
import tcc.job.devs.payloads.UserPayloads;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "language", source = "entity.language", qualifiedByName = "languageSkillsToLanguageIds")
    UserPayloads.UserModel toModel(UserEntity entity);

    UserEntity toEntity(UserPayloads.CreateUserPayload createUserPayload);

    @Mapping(target = "language", ignore = true)
    UserEntity toEntity(UserPayloads.UserWizard userWizard);

    UserEntity toEntity(UserPayloads.UpdateUserPayload updateUserPayload);

    UserEntity toEntity(UserPayloads.UserModel model);


    @Named("languageSkillsToLanguageIds")
    default UserLanguagePayloads.UserLanguageModel languageSkillsToLanguageIds(UserLanguageEntity userLanguage) {
        UserLanguagePayloads.UserLanguageModel userLanguageModel = new UserLanguagePayloads.UserLanguageModel();
        userLanguageModel.setId(userLanguage.getId());
        userLanguageModel.setEnglishProficiency(userLanguage.getEnglishProficiency());
        userLanguageModel.setWorkedWithEnglish(userLanguageModel.isWorkedWithEnglish());
        userLanguageModel.setLanguageSkills(new HashSet<>());
        for (UserLanguageSkillEntity userLanguageSkillEntity : userLanguage.getLanguageSkills()) {
            UserLanguagePayloads.UserLanguageSkillPayload userLanguageSkillPayload = new UserLanguagePayloads.UserLanguageSkillPayload();
            userLanguageSkillPayload.setId(userLanguageSkillEntity.getId());
            userLanguageSkillPayload.setProficiency(userLanguageSkillEntity.getProficiency());
            userLanguageSkillPayload.setLanguageId(userLanguageSkillEntity.getLanguage().getId());
            userLanguageModel.getLanguageSkills().add(userLanguageSkillPayload);
        }
        return userLanguageModel;
    }

    @Mapping(target = "language", ignore = true)
    void updateEntityFromPayload(UserPayloads.UserWizard model, @MappingTarget UserEntity entity);

    void updateModelFromEntity(UserEntity entity, @MappingTarget UserPayloads.UserModel model);

}
