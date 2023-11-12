package tcc.job.devs.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.SkillEntity;
import tcc.job.devs.payloads.SkillPayloads;

import java.util.Set;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    @Mapping(target = "userId", ignore = true)
    SkillPayloads.SkillModel toModel(SkillEntity entity);

    @Mapping(target = "userId", ignore = true)
    Set<SkillEntity> toEntitySet(Set<SkillPayloads.CreateSkillPayload> payload);

    SkillEntity toEntity(SkillPayloads.CreateSkillPayload createProfilePayload);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromPayload(SkillPayloads.CreateSkillPayload model, @MappingTarget SkillEntity entity);

}
