package tcc.job.devs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.entities.UserEntity;
import tcc.job.devs.mappers.UserMapper;
import tcc.job.devs.payloads.UserPayloads;
import tcc.job.devs.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserPayloads.UserModel findById(int id) {
        return userRepository.findById(id).map(UserMapper.INSTANCE::toModel).orElseThrow();
    }

    public UserPayloads.UserModel create(UserPayloads.CreateUserPayload createUserPayload) {
        UserEntity user = UserMapper.INSTANCE.toEntity(createUserPayload);
        userRepository.save(user);
        return UserMapper.INSTANCE.toModel(user);
    }

    public UserPayloads.UserModel update(UserPayloads.UpdateUserPayload updateUserPayload) {
        UserEntity user = userRepository.findById(updateUserPayload.getId()).orElseThrow();
        UserMapper.INSTANCE.updateEntityFromPayload(updateUserPayload, user);
        userRepository.save(user);
        return UserMapper.INSTANCE.toModel(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

}
