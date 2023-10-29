package tcc.job.devs.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.ProfileEntity;
import tcc.job.devs.payloads.ProfilePayloads;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(target = "userId", ignore = true)
    ProfilePayloads.ProfileModel toModel(ProfileEntity entity);

    ProfileEntity toEntity(ProfilePayloads.CreateProfilePayload createProfilePayload);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromPayload(ProfilePayloads.UpdateProfilePayload model, @MappingTarget ProfileEntity entity);

}
