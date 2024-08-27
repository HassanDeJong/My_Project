package com.example.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Project.tables.Users;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Users,Integer> {
    @Query(value = "SELECT * from users where username = ?1",nativeQuery = true)
    Optional<Users> check_existing_user(String username);

    @Query(value = "SELECT * from users WHERE username = ?1 and password = ?2",nativeQuery=true)
    Optional<Users> login_authentication(String username,String password);
    
}
