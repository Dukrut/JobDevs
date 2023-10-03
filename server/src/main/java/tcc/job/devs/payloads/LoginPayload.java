package tcc.job.devs.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginPayload {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}