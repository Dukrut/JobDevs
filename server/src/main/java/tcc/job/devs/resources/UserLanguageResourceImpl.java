package tcc.job.devs.resources;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.job.devs.payloads.UserLanguagePayloads;
import tcc.job.devs.services.UserLanguageService;

@RestController
@RequestMapping(value = "/api/user/language")
public class UserLanguageResourceImpl implements IResponseResource {

    @Autowired
    private UserLanguageService userLanguageService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserLanguagePayloads.CreateUserLanguagePayload createUserPayload) {
        try {
            UserLanguagePayloads.UserLanguageModel model = userLanguageService.create(createUserPayload);
            return response(model, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return response(HttpStatus.BAD_REQUEST);
        }
    }

}
