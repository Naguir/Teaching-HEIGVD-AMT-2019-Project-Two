package ch.heigvd.user.repositories;

import ch.heigvd.user.entities.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

    @Transactional
    @Modifying
    @Query("UPDATE ch.heigvd.user.entities.UserEntity u SET u.password = :password WHERE u.email = :email")
    int updatePassword(@Param("password") String password, @Param("email") String email);

}

