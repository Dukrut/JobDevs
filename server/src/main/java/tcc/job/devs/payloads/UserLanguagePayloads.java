package tcc.job.devs.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.enums.Proficiency;

import java.util.HashSet;
import java.util.Set;

public class UserLanguagePayloads {

    @Data
    private static class UserLanguagePayload {
        private boolean workedWithEnglish;
        private Proficiency englishProficiency;
        private Set<UserLanguageSkillPayload> languageSkills = new HashSet<>();
    }

    @EqualsAndHashCode
    @ToString(callSuper = true)
    @Data
    public static class UserLanguageSkillPayload {
        private Proficiency proficiency;
        private int languageId;
    }

    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    @Data
    public static class CreateUserLanguagePayload extends UserLanguagePayload {
        @NotNull
        private int userId;
    }

    @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
    @ToString(callSuper = true)
    @Data
    public static class UpdateUserLanguagePayload extends UserLanguagePayload {
        @EqualsAndHashCode.Include
        private int id;
    }

    @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
    @ToString(callSuper = true)
    @Data
    public static class UserLanguageModel extends UserLanguagePayload {
        @EqualsAndHashCode.Include
        private int id;
    }

}