package tcc.job.devs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.job.devs.payloads.UserPayloads;
import tcc.job.devs.services.UserService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserResource implements BaseResource<UserPayloads.UserModel, UserPayloads.CreateUserPayload, UserPayloads.UpdateUserPayload> {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> get(int id) {
        try {
            UserPayloads.UserModel user = userService.findById(id);
            return response(user, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return response(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> create(UserPayloads.CreateUserPayload createUserPayload) {
        try {
            UserPayloads.UserModel model = userService.create(createUserPayload);
            return response(model, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return response(getDuplicatedFieldFromException(ex), HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> update(UserPayloads.UpdateUserPayload updateUserPayload) {
        try {
            UserPayloads.UserModel model = userService.update(updateUserPayload);
            return response(model, HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        try {
            userService.deleteById(id);
            return response(HttpStatus.OK);
        } catch (Exception ex) {
            return response(HttpStatus.BAD_REQUEST);
        }

    }
}
