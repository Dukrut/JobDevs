package tcc.job.devs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import tcc.job.devs.entities.EducationEntity;
import tcc.job.devs.payloads.EducationPayloads;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    EducationPayloads.EducationModel toModel(EducationEntity entity);

    EducationEntity toEntity(EducationPayloads.EducationPayload educationPayload);


}
