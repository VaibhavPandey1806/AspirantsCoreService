package com.vaibhav.repos;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.vaibhav.data.dao.User;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

    @Query("{ 'EmailId': ?0  }")
    List<User> findByEmailId(String emailId);
}
