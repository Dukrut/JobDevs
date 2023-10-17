package tcc.job.devs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tcc.job.devs.enums.Proficiency;

@EqualsAndHashCode(callSuper = true)
@Table(name = "user_has_languages")
@Entity
@Data
public class UserLanguageProficiencyEntity extends EntityBase {

    @Enumerated(EnumType.STRING)
    @Column(name = "proficiency")
    private Proficiency proficiency;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false)
    @NotNull
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", updatable = false)
    @NotNull
    private LanguageEntity language;


}
