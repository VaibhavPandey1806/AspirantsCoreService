package com.vaibhav.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaibhav.data.dao.Comments;

@Repository
public interface CommentRepository extends MongoRepository<Comments, String> {

	 @Query("{ 'id': { $in: ?0 } }")
	List<Comments> findByIdList(List<String> id);
//    Optional<Comments> findByName(String name); // Find a section by name
}
