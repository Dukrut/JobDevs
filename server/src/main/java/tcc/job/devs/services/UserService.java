package tcc.job.devs.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.LanguageEntity;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.entities.UserLanguageEntity;
import tcc.job.devs.entities.UserLanguageSkillEntity;
import tcc.job.devs.mappers.*;
import tcc.job.devs.payloads.UserLanguagePayloads;
import tcc.job.devs.payloads.UserPayloads;
import tcc.job.devs.repositories.UserLanguageRepositoryImpl;
import tcc.job.devs.repositories.UserRepositoryImpl;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private UserLanguageRepositoryImpl userLanguageRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserPayloads.UserModel findModelById(int id) {
        return userRepository.findById(id).map(UserMapper.INSTANCE::toModel).orElseThrow();
    }

    public UserEntity findEntityById(int id) {
        return userRepository.findById(id).orElseThrow();
    }


    public UserPayloads.UserModel create(UserPayloads.CreateUserPayload createUserPayload) {
        createUserPayload.setPassword(handlePassword(createUserPayload.getPassword()));
        UserEntity user = UserMapper.INSTANCE.toEntity(createUserPayload);
        userRepository.save(user);
        return UserMapper.INSTANCE.toModel(user);
    }

    public UserPayloads.UserModel handleWizard(UserPayloads.UserWizard userWizard) {

        userWizard.setPassword(handlePassword(userWizard.getPassword()));
        UserEntity user = UserMapper.INSTANCE.toEntity(userWizard);

        UserLanguageEntity userLanguage = UserLanguageMapper.INSTANCE.toEntity(userWizard.getLanguage());
        userLanguage.setUser(user);
        userLanguage.getLanguageSkills().clear();
        for (UserLanguagePayloads.UserLanguageSkillPayload userLanguageSkillPayload : userWizard.getLanguage().getLanguageSkills()) {
            UserLanguageSkillEntity userLanguageSkillEntity = new UserLanguageSkillEntity();
            userLanguageSkillEntity.setUserLanguage(userLanguage);
            userLanguageSkillEntity.setProficiency(userLanguageSkillPayload.getProficiency());
            LanguageEntity languageEntity = languageService.getEntityById(userLanguageSkillPayload.getLanguageId());
            userLanguageSkillEntity.setLanguage(languageEntity);
            userLanguage.getLanguageSkills().add(userLanguageSkillEntity);
        }

        user.setLanguage(userLanguage);
        user.getProfile().setUser(user);
        user.getSkills().forEach(skillEntity -> skillEntity.setUser(user));
        user.getEducations().forEach(educationEntity -> educationEntity.setUser(user));

        userRepository.save(user);
        return UserMapper.INSTANCE.toModel(user);

    }

    @Transactional
    public UserPayloads.UserModel update(UserPayloads.UserWizard userWizard) {
        userLanguageRepository.clearLanguageSkillsByLanguageId(userWizard.getLanguage().getId());
        userRepository.deleteProfileById(userWizard.getId());
        userRepository.deleteSkillsById(userWizard.getId());
        userRepository.deleteEducationsById(userWizard.getId());
        UserEntity user = userRepository.findById(userWizard.getId()).orElseThrow();
        user.setId(userWizard.getId());

        UserLanguageEntity userLanguage = UserLanguageMapper.INSTANCE.toEntity(userWizard.getLanguage());
        userLanguage.setUser(user);
        userLanguage.setId(userWizard.getLanguage().getId());
        for (UserLanguagePayloads.UserLanguageSkillPayload userLanguageSkillPayload : userWizard.getLanguage().getLanguageSkills()) {
            UserLanguageSkillEntity userLanguageSkillEntity = new UserLanguageSkillEntity();
            userLanguageSkillEntity.setUserLanguage(userLanguage);
            userLanguageSkillEntity.setProficiency(userLanguageSkillPayload.getProficiency());
            LanguageEntity languageEntity = languageService.getEntityById(userLanguageSkillPayload.getLanguageId());
            userLanguageSkillEntity.setLanguage(languageEntity);
            userLanguage.getLanguageSkills().add(userLanguageSkillEntity);
        }

        user.setLanguage(userLanguage);
        user.setProfile(ProfileMapper.INSTANCE.toEntity(userWizard.getProfile()));
        user.getProfile().setUser(user);
        user.setSkills(SkillMapper.INSTANCE.toEntitySet(userWizard.getSkills()));
        user.getSkills().forEach(skillEntity -> skillEntity.setUser(user));
        user.setEducations(EducationMapper.INSTANCE.toEntitySet(userWizard.getEducations()));
        user.getEducations().forEach(educationEntity -> educationEntity.setUser(user));
        userRepository.save(user);
        return UserMapper.INSTANCE.toModel(user);

    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public UserPayloads.UserModel getByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper.INSTANCE::toModel).orElseThrow();
    }

    private String handlePassword(String password) {
        return encoder.encode(password);
    }

    private String handleAndCheckPasswordHasChanged(String newPassword, String oldPassword) {
        return (Objects.isNull(newPassword)) ? oldPassword : handlePassword(newPassword);
    }

}
