package tcc.job.devs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.LanguageEntity;
import tcc.job.devs.mappers.LanguageMapper;
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

    public LanguageEntity getEntityById(int id) {
        return languageRepository.findById(id).orElseThrow();
    }

    public Set<LanguagePayloads.LanguagePayload> getOptions() {
        Iterator<LanguageEntity> iterator = languageRepository.findAll().iterator();
        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(iterator, 0), false).map(LanguageMapper.INSTANCE::toModel)
                .collect(Collectors.toSet());
    }


}
