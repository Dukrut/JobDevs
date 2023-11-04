package tcc.job.devs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.payloads.UserPayloads;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserPayloads.UserModel toModel(UserEntity entity);

    UserEntity toEntity(UserPayloads.CreateUserPayload createUserPayload);

    @Mapping(target = "language", ignore = true)
    UserEntity toEntity(UserPayloads.UserWizard userWizard);

    UserEntity toEntity(UserPayloads.UpdateUserPayload updateUserPayload);

    UserEntity toEntity(UserPayloads.UserModel model);

    void updateEntityFromPayload(UserPayloads.UpdateUserPayload model, @MappingTarget UserEntity entity);

    void updateModelFromEntity(UserEntity entity, @MappingTarget UserPayloads.UserModel model);

}
