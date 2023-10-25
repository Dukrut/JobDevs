package tcc.job.devs.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.LanguageEntity;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.entities.UserLanguageEntity;
import tcc.job.devs.entities.UserLanguageSkillEntity;
import tcc.job.devs.mappers.UserLanguageMapper;
import tcc.job.devs.payloads.UserLanguagePayloads;
import tcc.job.devs.repositories.UserLanguageRepositoryImpl;

@Service
public class UserLanguageService {

    @Autowired
    private UserLanguageRepositoryImpl userLanguageRepository;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private UserService userService;

    public UserLanguagePayloads.UserLanguageModel create(UserLanguagePayloads.CreateUserLanguagePayload createUserLanguagePayload) {
        UserEntity userEntity = userService.findEntityById(createUserLanguagePayload.getUserId());
        UserLanguageEntity userLanguageEntity = UserLanguageMapper.INSTANCE.toEntity(createUserLanguagePayload);
        userLanguageEntity.setUser(userEntity);
        for (UserLanguagePayloads.UserLanguageSkillPayload userLanguageSkillPayload : createUserLanguagePayload.getLanguageSkills()) {
            UserLanguageSkillEntity userLanguageSkillEntity = new UserLanguageSkillEntity();
            userLanguageSkillEntity.setProficiency(userLanguageSkillPayload.getProficiency());
            userLanguageSkillEntity.setUserLanguage(userLanguageEntity);
            LanguageEntity languageEntity = languageService.getEntityById(userLanguageSkillPayload.getLanguageId());
            userLanguageSkillEntity.setLanguage(languageEntity);
            userLanguageEntity.getLanguageSkills().add(userLanguageSkillEntity);
        }
        return UserLanguageMapper.INSTANCE.toModel(userLanguageRepository.save(userLanguageEntity));

    }

    @Transactional
    public UserLanguagePayloads.UserLanguageModel update(UserLanguagePayloads.UpdateUserLanguagePayload updateUserLanguagePayload) {
        UserLanguageEntity userLanguageEntity = userLanguageRepository.findById(updateUserLanguagePayload.getId()).orElseThrow();
        userLanguageRepository.clearLanguageSkillsByLanguageId(userLanguageEntity.getId());
        userLanguageEntity.getLanguageSkills().clear();
        for (UserLanguagePayloads.UserLanguageSkillPayload userLanguageSkillPayload : updateUserLanguagePayload.getLanguageSkills()) {
            UserLanguageSkillEntity userLanguageSkillEntity = new UserLanguageSkillEntity();
            userLanguageSkillEntity.setProficiency(userLanguageSkillPayload.getProficiency());
            userLanguageSkillEntity.setUserLanguage(userLanguageEntity);
            LanguageEntity languageEntity = languageService.getEntityById(userLanguageSkillPayload.getLanguageId());
            userLanguageSkillEntity.setLanguage(languageEntity);
            userLanguageEntity.getLanguageSkills().add(userLanguageSkillEntity);
        }
        return UserLanguageMapper.INSTANCE.toModel(userLanguageRepository.save(userLanguageEntity));
    }

    public UserLanguagePayloads.UserLanguageModel getByUserId(int userId) {
        return UserLanguageMapper.INSTANCE.toModel(userLanguageRepository.findByUserId(userId).orElseThrow());
    }
}
