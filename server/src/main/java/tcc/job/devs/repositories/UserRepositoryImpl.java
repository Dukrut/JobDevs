package tcc.job.devs.repositories;

import tcc.job.devs.entities.UserEntity;

import java.util.Optional;

public interface UserRepositoryImpl extends BaseRepository<UserEntity> {

    Optional<UserEntity> findByEmail(String email);

}
