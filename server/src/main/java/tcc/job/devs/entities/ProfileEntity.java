package tcc.job.devs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.converters.ContractPreferenceConverter;
import tcc.job.devs.converters.JobPreferenceConverter;
import tcc.job.devs.enums.ContractPreference;
import tcc.job.devs.enums.Gender;
import tcc.job.devs.enums.JobPreference;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "profiles")
@Entity
@Data
@ToString(callSuper = true)
public class ProfileEntity extends EntityBase {

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "last_role")
    private String lastRole;

    @Column(name = "last_company")
    private String lastCompany;
    
    @Convert(converter = JobPreferenceConverter.class)
    @Column(name = "job_preference", nullable = false)
    private Set<JobPreference> jobPreference = new HashSet<>();

    @Convert(converter = ContractPreferenceConverter.class)
    @Column(name = "contract_preference")
    private Set<ContractPreference> contractPreference = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @NotNull
    @ToString.Exclude
    private UserEntity user;
}
