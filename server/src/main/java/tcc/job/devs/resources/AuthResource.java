package tcc.job.devs.resources;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.job.devs.payloads.LoginPayload;
import tcc.job.devs.security.JwtUtils;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthResource implements IResponseResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginPayload loginPayload) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginPayload.getEmail(), loginPayload.getPassword())
        );

        try {
            String jwtToken = jwt.generateJwtToken(authentication);
            return response(jwtToken, HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

}
