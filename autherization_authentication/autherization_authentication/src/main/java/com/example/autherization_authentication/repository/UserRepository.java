package com.example.autherization_authentication.repository;

import com.example.autherization_authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByName(@Param("username") String username);

    // Optional<User> findByName(String username);

}
