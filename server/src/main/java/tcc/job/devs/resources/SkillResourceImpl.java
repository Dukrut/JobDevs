package tcc.job.devs.resources;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.job.devs.payloads.SkillPayloads;
import tcc.job.devs.services.SkillService;

import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/user/{user_id}/skill")
public class SkillResourceImpl implements IResponseResource {


    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<?> get(@PathVariable(name = "user_id") int userId) {
        try {
            Set<SkillPayloads.SkillModel> modelSet = skillService.findByUserId(userId);
            return response(modelSet, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return response(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Set<SkillPayloads.CreateSkillPayload> payloads, @PathVariable(name = "user_id") int userId) {
        try {
            Set<SkillPayloads.SkillModel> modelSet = skillService.create(payloads, userId);
            return response(modelSet, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return response(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Set<SkillPayloads.CreateSkillPayload> payloads, @PathVariable(name = "user_id") int userId) {
        try {
            Set<SkillPayloads.SkillModel> modelSet = skillService.update(payloads, userId);
            return response(modelSet, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return response(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }


}
