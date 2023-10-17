package tcc.job.devs.payloads;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tcc.job.devs.enums.Proficiency;

public class LanguagePayloads {

    @Data
    public static class LanguagePayload {
        private int id;
        private String name;
    }

    @Data
    public static class UpdateUserLanguagePayload {

        private int languageId;
        private Proficiency proficiency;
    }

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class UserLanguageProficiencyModel {

        @EqualsAndHashCode.Include
        private int id;
        private Proficiency proficiency;
        private LanguagePayload language;

    }
}
