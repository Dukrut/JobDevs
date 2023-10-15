package tcc.job.devs.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tcc.job.devs.entities.SkillEntity;
import tcc.job.devs.enums.ExperienceTime;

import java.util.Set;

public interface SkillRepository extends BaseRepository<SkillEntity> {

    Set<SkillEntity> findByUserId(int userId);

    @Modifying
    @Query(value = "INSERT INTO skills (name, experience_time, user_id VALUES (:name, :experienceTime, :userId)", nativeQuery = true)
    SkillEntity save(String name, ExperienceTime experienceTime, int userId);

    @Modifying
    @Query(value = "DELETE FROM skill WHERE userId = :userId", nativeQuery = true)
    void deleteAllByUserId(int userId);
}
