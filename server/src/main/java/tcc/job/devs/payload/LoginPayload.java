package tcc.job.devs.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginPayload {

    @NotBlank
    @Email
    private String mail;

    @NotBlank
    private String password;

}