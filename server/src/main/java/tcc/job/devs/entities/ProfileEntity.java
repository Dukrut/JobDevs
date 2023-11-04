package tcc.job.devs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.enums.ContractPreference;
import tcc.job.devs.enums.Gender;
import tcc.job.devs.enums.JobPreference;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "job_preference", nullable = false)
    private JobPreference jobPreference;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_preference")
    private ContractPreference contractPreference;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @NotNull
    @ToString.Exclude
    private UserEntity user;
}
