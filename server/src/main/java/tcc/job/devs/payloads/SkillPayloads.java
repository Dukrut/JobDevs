package tcc.job.devs.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.enums.ExperienceTime;

public class SkillPayloads {

    @Data
    private static class SkillPayload {
        @NotBlank
        private String name;
        @NotBlank
        private ExperienceTime experienceTime;
    }

    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    @Data
    public static class CreateSkillPayload extends SkillPayload {
    }

    @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
    @ToString(callSuper = true)
    @Data
    public static class SkillModel extends SkillPayload {
        @EqualsAndHashCode.Include
        private Integer id;
        @NotNull
        private int userId;
    }

}

