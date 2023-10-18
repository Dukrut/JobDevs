package tcc.job.devs.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.EducationEntity;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.mappers.EducationMapper;
import tcc.job.devs.payloads.EducationPayloads;
import tcc.job.devs.repositories.EducationRepositoryImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EducationService {

    @Autowired
    private EducationRepositoryImpl educationRepository;

    @Autowired
    private UserService userService;

    public Set<EducationPayloads.EducationModel> getUserLanguages(int userId) {
        return educationRepository.findAllByUserId(userId).stream().map(EducationMapper.INSTANCE::toModel).collect(Collectors.toSet());
    }


    @Transactional
    public Set<EducationPayloads.EducationModel> updateUserEducation(Set<EducationPayloads.EducationPayload> educationPayloads, int userId) {
        UserEntity user = userService.findEntityById(userId);
        educationRepository.deleteUserEducationsById(userId);
        Set<EducationPayloads.EducationModel> modelSet = new HashSet<>();
        for (EducationPayloads.EducationPayload payload : educationPayloads) {
            EducationEntity entity = EducationMapper.INSTANCE.toEntity(payload);
            entity.setUser(user);
            educationRepository.save(entity);
            modelSet.add(EducationMapper.INSTANCE.toModel(entity));
        }
        return modelSet;
    }

}
