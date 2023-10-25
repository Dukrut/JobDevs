package tcc.job.devs.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tcc.job.devs.entities.UserLanguageEntity;

import java.util.Optional;

public interface UserLanguageRepositoryImpl extends BaseRepository<UserLanguageEntity> {

    Optional<UserLanguageEntity> findByUserId(int userId);


    @Modifying
    @Query(value = "DELETE FROM user_languages_has_skills WHERE user_language_id =?1", nativeQuery = true)
    void clearLanguageSkillsByLanguageId(int id);

}
