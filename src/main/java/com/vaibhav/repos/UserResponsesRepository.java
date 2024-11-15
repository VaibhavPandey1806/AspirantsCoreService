package com.vaibhav.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaibhav.data.dao.Comments;
import com.vaibhav.data.dao.UserResponses;

@Repository
public interface UserResponsesRepository extends MongoRepository<UserResponses, String>
{

	
	 List<UserResponses> findByUserid(String userid);
//	UserResponses findbyuserid(String userId);


}
