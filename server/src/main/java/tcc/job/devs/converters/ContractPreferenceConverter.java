package tcc.job.devs.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tcc.job.devs.enums.ContractPreference;
import tcc.job.devs.enums.JobPreference;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class ContractPreferenceConverter implements AttributeConverter<Set<ContractPreference>, String> {

    @Override
    public String convertToDatabaseColumn(Set<ContractPreference> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    @Override
    public Set<ContractPreference> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(dbData.split(","))
                .map(ContractPreference::valueOf)
                .collect(Collectors.toSet());
    }
}