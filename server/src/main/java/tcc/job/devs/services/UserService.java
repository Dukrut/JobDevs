package tcc.job.devs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.payload.UserPayloads;
import tcc.job.devs.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserPayloads.UserModel getById() {
        return null;
    }
}
