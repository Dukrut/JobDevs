package tcc.job.devs.payloads;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.enums.ContractPreference;
import tcc.job.devs.enums.Gender;
import tcc.job.devs.enums.JobPreference;

import java.util.HashSet;
import java.util.Set;

public class ProfilePayloads {

    @Data
    private static class ProfilePayload {
        @NotBlank
        private String description;
        private Gender gender;
        private String hobbies;
        private String lastRole;
        private String lastCompany;
        @NotNull
        private Set<JobPreference> jobPreference = new HashSet<>();
        private Set<ContractPreference> contractPreference = new HashSet<>();
    }

    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    @Data
    public static class CreateProfilePayload extends ProfilePayload {
        @NotNull
        private int userId;
    }

    @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
    @ToString(callSuper = true)
    @Data
    public static class UpdateProfilePayload extends ProfilePayload {
        @EqualsAndHashCode.Include
        @NotNull
        private Integer id;
    }

    @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
    @ToString(callSuper = true)
    @Data
    public static class ProfileModel extends ProfilePayload {
        @EqualsAndHashCode.Include
        private Integer id;
        @NotNull
        @JsonBackReference
        private int userId;
    }

}

