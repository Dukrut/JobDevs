package tcc.job.devs.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CreateUserPayload extends UserPayload {
        @NotBlank
        private String password;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UpdateUserPayload extends UserPayload {
        private int id;
        @NotBlank
        private String password;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserModel extends UserPayload {
        private int id;
        @JsonIgnore
        private String password;
    }

}