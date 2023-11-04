package tcc.job.devs.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.enums.Proficiency;

@EqualsAndHashCode(callSuper = true)
@Table(name = "user_languages_has_skills")
@Entity
@Data
@ToString(callSuper = true)
public class UserLanguageSkillEntity extends EntityBase {

    @OneToOne
    @JoinColumn(name = "language_id")
    private LanguageEntity language;

    @Enumerated(EnumType.STRING)
    @Column(name = "proficiency")
    private Proficiency proficiency;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_language_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserLanguageEntity userLanguage;
}
