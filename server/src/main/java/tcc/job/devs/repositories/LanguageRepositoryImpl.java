package tcc.job.devs.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tcc.job.devs.entities.LanguageEntity;
import tcc.job.devs.entities.UserLanguageProficiencyEntity;

import java.util.Set;

public interface LanguageRepositoryImpl extends BaseRepository<LanguageEntity> {

    @Modifying
    @Query(value = "DELETE FROM user_has_languages WHERE user_id = ?1", nativeQuery = true)
    void deleteUserLanguagesById(int userId);

    @Modifying
    @Query(value = "INSERT INTO user_has_languages (user_id, language_id, proficiency) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertLanguageToUser(int userId, int languageId, String proficiency);

    @Query("SELECT ulp FROM UserLanguageProficiencyEntity ulp JOIN ulp.user u WHERE u.id = ?1")
    Set<UserLanguageProficiencyEntity> findAllByUserId(int userId);
}
