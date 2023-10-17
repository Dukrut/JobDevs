package tcc.job.devs.resources;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.job.devs.payloads.LanguagePayloads;
import tcc.job.devs.services.LanguageService;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/languages")
public class LanguageResourceImpl implements IResponseResource {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/options")
    public ResponseEntity<?> getOptions() {
        try {
            Set<LanguagePayloads.LanguagePayload> modelSet = languageService.getOptions();
            return response(modelSet, HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getByUser(@PathVariable(name = "user_id") int userId) {
        try {
            Set<LanguagePayloads.UserLanguageProficiencyModel> modelSet = languageService.getUserLanguages(userId);
            return response(modelSet, HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{user_id}")
    public ResponseEntity<?> updateUserLanguages(@Valid @RequestBody Set<LanguagePayloads.UpdateUserLanguagePayload> payload, @PathVariable(name = "user_id") int userId) {
        try {
            Set<LanguagePayloads.UserLanguageProficiencyModel> modelSet = languageService.updateUserLanguages(payload, userId);
            return response(modelSet, HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

}
