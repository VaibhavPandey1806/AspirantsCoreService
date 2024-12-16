package com.vaibhav.repos;

import com.vaibhav.data.dao.Question;
import com.vaibhav.data.dao.QuestionPending;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionPendingRepository extends MongoRepository<QuestionPending, String> {

    // Find a question by its ID
    Optional<QuestionPending> findById(String id);

    // Find questions by Section ID
    List<QuestionPending> findBySectionId(String sectionId);

    // Find questions by Topic ID
    List<QuestionPending> findByTopicId(String topicId);

    // Find questions by Section and Topic ID (if necessary)
    List<QuestionPending> findBySectionIdAndTopicId(String sectionId, String topicId);

    @Query("{ 'sectionId': { $in: ?0 }, 'topicId': { $in: ?1 } }")
    List<QuestionPending> findBySectionInAndTopicIn(List<String> categories, List<String> topics);

    @Query("{ 'sectionId': { $in: ?0 }, 'topicId': { $in: ?1 },'SourceId': { $in: ?2 } }")
    List<QuestionPending> findByFilters(List<String> category, List<String> topic, List<String> source);

    @Query("{ 'approved':false}")
    List<QuestionPending> findNotApproved();


    @Query("{ 'submittedBy':userId}")
    List<QuestionPending> findbyUserId(String userId);
}
