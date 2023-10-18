package tcc.job.devs.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tcc.job.devs.entities.EducationEntity;

import java.util.Set;

public interface EducationRepositoryImpl extends BaseRepository<EducationEntity> {

    @Modifying
    @Query(value = "DELETE FROM educations WHERE user_id = ?1", nativeQuery = true)
    void deleteUserEducationsById(int userId);

    @Query("SELECT e FROM EducationEntity e JOIN e.user u WHERE u.id = ?1")
    Set<EducationEntity> findAllByUserId(int userId);

}
