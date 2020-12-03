package com.xzit.project.repository;

import com.xzit.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findUserByPhone(String phone);

    List<User> findUsersByPhoneLike(String phone);

    List<User> findUsersByUsernameLike(String username);

    @Query("select u from User u where u.phone = ?1")
    Optional<User> login(String login);
}
