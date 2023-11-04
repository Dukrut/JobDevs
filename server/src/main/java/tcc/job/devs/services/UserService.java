package tcc.job.devs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.LanguageEntity;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.entities.UserLanguageEntity;
import tcc.job.devs.entities.UserLanguageSkillEntity;
import tcc.job.devs.mappers.UserLanguageMapper;
import tcc.job.devs.mappers.UserMapper;
import tcc.job.devs.payloads.UserLanguagePayloads;
import tcc.job.devs.payloads.UserPayloads;
import tcc.job.devs.repositories.UserRepositoryImpl;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private LanguageService languageService;

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

    public UserPayloads.UserModel update(UserPayloads.UpdateUserPayload updateUserPayload) {
        UserEntity user = userRepository.findById(updateUserPayload.getId()).orElseThrow();
        updateUserPayload.setPassword(handleAndCheckPasswordHasChanged(updateUserPayload.getPassword(), user.getPassword()));
        UserMapper.INSTANCE.updateEntityFromPayload(updateUserPayload, user);
        userRepository.save(user);
        return UserMapper.INSTANCE.toModel(user);
    }

    public UserPayloads.UserModel handleWizard(UserPayloads.UserWizard userWizard) {

        userWizard.setPassword(handlePassword(userWizard.getPassword()));
        UserEntity user = UserMapper.INSTANCE.toEntity(userWizard);

        // iDIOMAS
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
