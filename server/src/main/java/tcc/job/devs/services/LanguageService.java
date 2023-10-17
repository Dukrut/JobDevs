package tcc.job.devs.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.LanguageEntity;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.mappers.LanguageMapper;
import tcc.job.devs.mappers.UserLanguageProficiencyMapper;
import tcc.job.devs.payloads.LanguagePayloads;
import tcc.job.devs.repositories.LanguageRepositoryImpl;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepositoryImpl languageRepository;

    @Autowired
    private UserService userService;

    public Set<LanguagePayloads.LanguagePayload> getOptions() {
        Iterator<LanguageEntity> iterator = languageRepository.findAll().iterator();
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(iterator, 0), false).map(LanguageMapper.INSTANCE::toModel)
                .collect(Collectors.toSet());
    }

    public Set<LanguagePayloads.UserLanguageProficiencyModel> getUserLanguages(int userId) {
        return languageRepository.findAllByUserId(userId).stream().map(UserLanguageProficiencyMapper.INSTANCE::toModel).collect(Collectors.toSet());
    }


    @Transactional
    public Set<LanguagePayloads.UserLanguageProficiencyModel> updateUserLanguages(Set<LanguagePayloads.UpdateUserLanguagePayload> languagePayloads, int userId) {
        UserEntity user = userService.findEntityById(userId);
        languageRepository.deleteUserLanguagesById(userId);
        for (LanguagePayloads.UpdateUserLanguagePayload payload : languagePayloads) {
            languageRepository.insertLanguageToUser(user.getId(), payload.getLanguageId(), payload.getProficiency().toString());
        }
        return getUserLanguages(user.getId());
    }

}
