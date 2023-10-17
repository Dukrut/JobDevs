package tcc.job.devs.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tcc.job.devs.entities.SkillEntity;

import java.util.Set;

public interface SkillRepository extends BaseRepository<SkillEntity> {

    Set<SkillEntity> findByUserId(int userId);

    @Modifying
    @Query(value = "INSERT INTO skills (name, experience_time, user_id) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void save(String name, String experienceTime, int userId);

    @Modifying
    @Query(value = "DELETE FROM skills WHERE user_id = ?1", nativeQuery = true)
    void deleteAllByUserId(int userId);
}
