package com.vaibhav.repos;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.vaibhav.data.dao.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
