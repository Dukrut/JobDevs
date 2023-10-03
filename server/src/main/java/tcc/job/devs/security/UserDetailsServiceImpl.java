package tcc.job.devs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tcc.job.devs.payloads.UserPayloads;
import tcc.job.devs.services.UserService;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserPayloads.UserModel user = userService.getByEmail(email);

        if (Objects.isNull(user))
            throw new UsernameNotFoundException("Nenhum usuário encontrado com o e-mail: " + email);

        return UserDetailsImpl.build(user);

    }
}
