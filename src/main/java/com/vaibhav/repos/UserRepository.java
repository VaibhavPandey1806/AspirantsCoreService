package com.vaibhav.repos;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.vaibhav.data.dao.User;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);

//    User findByEmail(String email);

    @Query("{ 'Mobile': ?0  }")
    List<User> findbyMobile(String mobile);


    @Query("{ 'EmailId': ?0  }")
    List<User> findByEmailId(String emailId);

//    @Query("{ 'username': ?0 }")
//    boolean existsByUsername(String username);
//
//
//    // Check if email ID exists
//    @Query("{ 'EmailId': ?0 }")
//    boolean existsByEmailId(String emailId);
//
//    // Check if mobile exists
//    @Query("{ 'Mobile': ?0 }")
//    boolean existsByMobile(String mobile);

}
