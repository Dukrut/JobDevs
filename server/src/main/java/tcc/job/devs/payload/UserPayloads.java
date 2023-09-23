package tcc.job.devs.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserPayloads {

    @Data
    private static class UserPayload {
        @NotBlank
        private String name;
        @NotBlank
        @Email
        private String email;
        @NotBlank
        private String password;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CreateUserPayload extends UserPayload {
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UpdateUserPayload extends UserPayload {
        private int id;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserModel extends UserPayload {
        private int id;
    }

}