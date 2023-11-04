package tcc.job.devs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcc.job.devs.payloads.UserPayloads;
import tcc.job.devs.security.JwtUtils;

@Service
public class JobService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    public void searchJobForUser() {

        int userId = jwtUtils.getUserIdFromJWT();
        UserPayloads.UserModel userModel = userService.findModelById(userId);
        System.out.println(userModel);

    }
}
