package com.csye5.CloudComputing.dao;

import com.csye5.CloudComputing.repository.User;
import jakarta.persistence.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User,String> {
    Optional<User> findUserByUsername(String username);
}
