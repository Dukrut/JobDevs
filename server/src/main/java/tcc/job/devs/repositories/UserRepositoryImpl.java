package tcc.job.devs.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tcc.job.devs.entities.UserEntity;

import java.util.Optional;

public interface UserRepositoryImpl extends BaseRepository<UserEntity> {

    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM profiles WHERE user_id = ?1", nativeQuery = true)
    void deleteProfileById(int userId);

    @Modifying
    @Query(value = "DELETE FROM skills WHERE user_id = ?1", nativeQuery = true)
    void deleteSkillsById(int userId);

    @Modifying
    @Query(value = "DELETE FROM educations WHERE user_id = ?1", nativeQuery = true)
    void deleteEducationsById(int userId);
}
