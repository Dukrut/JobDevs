package tcc.job.devs.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @ToString(callSuper = true)
    @Data
    public static class CreateUserPayload extends UserPayload {
        @NotBlank
        private String password;
    }

    @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
    @ToString(callSuper = true)
    @Data
    public static class UpdateUserPayload extends UserPayload {
        @EqualsAndHashCode.Include
        private int id;
        private String password;
    }

    @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
    @ToString(callSuper = true)
    @Data
    public static class UserModel extends UserPayload {
        @EqualsAndHashCode.Include
        private int id;
        @JsonIgnore
        private String password;
    }

}