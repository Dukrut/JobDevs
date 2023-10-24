package tcc.job.devs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
