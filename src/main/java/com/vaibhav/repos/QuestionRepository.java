package com.vaibhav.repos;

import com.vaibhav.data.dao.Question;
import com.vaibhav.data.dao.QuestionPending;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {

    // Find a question by its ID
    Optional<Question> findById(String id);

    // Find questions by Section ID
    List<Question> findBySectionId(String sectionId);

    // Find questions by Topic ID
    List<Question> findByTopicId(String topicId);

    // Find questions by Section and Topic ID (if necessary)
    List<Question> findBySectionIdAndTopicId(String sectionId, String topicId);

    @Query("{ 'sectionId': { $in: ?0 }, 'topicId': { $in: ?1 } }")
    List<Question> findBySectionInAndTopicIn(List<String> categories, List<String> topics);

    @Query("{ 'sectionId': { $in: ?0 }, 'topicId': { $in: ?1 },'SourceId': { $in: ?2 } }")
	List<Question> findByFilters(List<String> category, List<String> topic, List<String> source);

    @Query("{ 'submittedBy':userId}")
    List<Question> findbyUserId(String userId);
}
