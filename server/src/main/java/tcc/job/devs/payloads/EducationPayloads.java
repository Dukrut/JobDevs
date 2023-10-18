package tcc.job.devs.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EducationPayloads {

    @Data
    public static class EducationPayload {
        @EqualsAndHashCode.Include
        private String institutionName;
        private String degree;
        private String fieldStudy;
        private Date startDate;
        private Date endDate;
        private String grade;
        private String description;
        @EqualsAndHashCode.Include
        private String activities;
    }

    @Data
    public static class UpdateUserEducationPayload {

        @NotNull
        private Set<EducationPayload> educationPayloadSet = new HashSet<>();
    }

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public static class EducationModel {

        @EqualsAndHashCode.Include
        private int id;
        private String institutionName;
        private String degree;
        private String fieldStudy;
        private Date startDate;
        private Date endDate;
        private String grade;
        private String description;
        private String activities;
    }
}
