package tcc.job.devs.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.job.devs.payload.UserPayloads;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserResource implements BaseResource<UserPayloads.UserModel, UserPayloads.CreateUserPayload, UserPayloads.UpdateUserPayload> {

    @Override
    public ResponseEntity<UserPayloads.UserModel> get(int id) {
        return null;
    }

    @Override
    public ResponseEntity<UserPayloads.UserModel> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<UserPayloads.UserModel> create(UserPayloads.CreateUserPayload createUserPayload) {
        return null;
    }

    @Override
    public ResponseEntity<UserPayloads.UserModel> update(UserPayloads.UpdateUserPayload updateUserPayload) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(int id) {
        return null;
    }
}
