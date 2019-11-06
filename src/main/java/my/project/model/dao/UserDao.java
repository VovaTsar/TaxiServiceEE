package my.project.model.dao;

import my.project.model.entity.UserEntity;

import java.util.Optional;

public interface UserDao extends CrudDao<Integer, UserEntity> {
    Optional<UserEntity> findByEmail(String email);

}
