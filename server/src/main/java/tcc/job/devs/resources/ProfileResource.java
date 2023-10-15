package tcc.job.devs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.job.devs.payloads.ProfilePayloads;
import tcc.job.devs.services.ProfileService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/user/profile")
public class ProfileResource implements BaseResource<ProfilePayloads.ProfileModel, ProfilePayloads.CreateProfilePayload, ProfilePayloads.UpdateProfilePayload> {

    @Autowired
    private ProfileService profileService;

    @Override
    public ResponseEntity<?> get(int id) {
        try {
            ProfilePayloads.ProfileModel user = profileService.findByUserId(id);
            return response(user, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return response(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> create(ProfilePayloads.CreateProfilePayload createProfilePayload) {
        try {
            ProfilePayloads.ProfileModel model = profileService.create(createProfilePayload);
            return response(model, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return response(getDuplicatedFieldFromException(ex), HttpStatus.CONFLICT);
        } catch (NoSuchElementException ex) {
            return response(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> update(ProfilePayloads.UpdateProfilePayload updateProfilePayload) {
        try {
            ProfilePayloads.ProfileModel model = profileService.update(updateProfilePayload);
            return response(model, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return response(getDuplicatedFieldFromException(ex), HttpStatus.CONFLICT);
        } catch (NoSuchElementException ex) {
            return response(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        return response(HttpStatus.NOT_IMPLEMENTED);
    }
}
