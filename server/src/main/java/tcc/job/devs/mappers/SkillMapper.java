package tcc.job.devs.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.SkillEntity;
import tcc.job.devs.payloads.SkillPayloads;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    @Mapping(target = "userId", ignore = true)
    SkillPayloads.SkillModel toModel(SkillEntity entity);

    SkillEntity toEntity(SkillPayloads.CreateSkillPayload createProfilePayload);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromPayload(SkillPayloads.CreateSkillPayload model, @MappingTarget SkillEntity entity);

}
