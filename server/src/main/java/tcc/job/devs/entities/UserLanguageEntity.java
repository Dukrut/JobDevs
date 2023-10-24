package tcc.job.devs.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tcc.job.devs.enums.Proficiency;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Table(name = "user_languages")
@Entity
@Data
@ToString(callSuper = true)
public class UserLanguageEntity extends EntityBase {

    @Column(name = "worked_with_english", nullable = false)
    private boolean workedWithEnglish;

    @Enumerated(EnumType.STRING)
    @Column(name = "english_proficiency", nullable = false)
    private Proficiency englishProficiency;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "userLanguage", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserLanguageSkillEntity> languageSkills = new HashSet<>();
}
