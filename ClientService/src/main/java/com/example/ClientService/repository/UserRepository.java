package com.example.ClientService.repository;


import com.example.ClientService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    @Query("SELECT u.username FROM User u WHERE u.id = :userId")
    String findUsernameById(@Param("userId") Long userId);
}
