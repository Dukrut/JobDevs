package tcc.job.devs.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tcc.job.devs.enums.JobPreference;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class JobPreferenceConverter implements AttributeConverter<Set<JobPreference>, String> {

    @Override
    public String convertToDatabaseColumn(Set<JobPreference> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    @Override
    public Set<JobPreference> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(dbData.split(","))
                .map(JobPreference::valueOf)
                .collect(Collectors.toSet());
    }
}