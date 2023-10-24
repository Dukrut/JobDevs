package tcc.job.devs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.enums.ExperienceTime;

@EqualsAndHashCode(callSuper = true)
@Table(name = "skills")
@Entity
@Data
@ToString(callSuper = true)
public class SkillEntity extends EntityBase {

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience_time")
    private ExperienceTime experienceTime;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @NotNull
    private UserEntity user;
}
