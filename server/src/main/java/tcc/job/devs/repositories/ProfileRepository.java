package tcc.job.devs.repositories;

import tcc.job.devs.entities.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository extends BaseRepository<ProfileEntity> {

    Optional<ProfileEntity> findByUserId(int userId);

}
