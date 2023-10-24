package tcc.job.devs.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Table(name = "language")
@Entity
@Data
@ToString(callSuper = true)
public class LanguageEntity extends EntityBase {

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

}
