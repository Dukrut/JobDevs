package tcc.job.devs.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(name = "educations")
@Entity
@Data
@ToString(callSuper = true)
public class EducationEntity extends EntityBase {

    @Column(name = "institution_name", nullable = false, length = 255)
    private String institutionName;

    @Column(name = "degree", length = 100)
    private String degree;

    @Column(name = "field_study", length = 255)
    private String fieldStudy;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "grade", length = 100)
    private String grade;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "activities", nullable = false, length = 255)
    private String activities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
