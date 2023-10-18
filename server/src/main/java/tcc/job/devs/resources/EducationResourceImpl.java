package tcc.job.devs.resources;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.job.devs.payloads.EducationPayloads;
import tcc.job.devs.services.EducationService;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/educations")
public class EducationResourceImpl implements IResponseResource {

    @Autowired
    private EducationService educationService;

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getByUser(@PathVariable(name = "user_id") int userId) {
        try {
            Set<EducationPayloads.EducationModel> modelSet = educationService.getUserLanguages(userId);
            return response(modelSet, HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user/{user_id}")
    public ResponseEntity<?> updateUserLanguages(@Valid @RequestBody Set<EducationPayloads.EducationPayload> payload, @PathVariable(name = "user_id") int userId) {
        try {
            Set<EducationPayloads.EducationModel> modelSet = educationService.updateUserEducation(payload, userId);
            return response(modelSet, HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

}
