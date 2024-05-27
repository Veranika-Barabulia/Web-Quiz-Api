package org.example.engine.repository;

import org.example.engine.entity.AppUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUserEntity, Integer> {
    Optional<AppUserEntity> findAppUserByUsername(String username);
    boolean existsAppUserByUsername(String username);
}
