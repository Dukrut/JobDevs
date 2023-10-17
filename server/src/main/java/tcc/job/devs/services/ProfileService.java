package tcc.job.devs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.ProfileEntity;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.mappers.ProfileMapper;
import tcc.job.devs.payloads.ProfilePayloads;
import tcc.job.devs.repositories.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserService userService;

    public ProfilePayloads.ProfileModel findByUserId(int id) {
        return profileRepository.findByUserId(id).map(ProfileMapper.INSTANCE::toModel).orElseThrow();
    }

    public ProfilePayloads.ProfileModel create(ProfilePayloads.CreateProfilePayload createProfilePayload) {
        UserEntity user = userService.findEntityById(createProfilePayload.getUserId());
        ProfileEntity profile = ProfileMapper.INSTANCE.toEntity(createProfilePayload);
        profile.setUser(user);
        profileRepository.save(profile);
        return ProfileMapper.INSTANCE.toModel(profile);
    }

    public ProfilePayloads.ProfileModel update(ProfilePayloads.UpdateProfilePayload updateProfilePayload) {
        ProfileEntity profile = profileRepository.findById(updateProfilePayload.getId()).orElseThrow();
        ProfileMapper.INSTANCE.updateEntityFromPayload(updateProfilePayload, profile);
        profileRepository.save(profile);
        return ProfileMapper.INSTANCE.toModel(profile);
    }

}
