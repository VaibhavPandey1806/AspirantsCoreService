package com.vaibhav.repos;

import com.vaibhav.data.dao.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    Optional<Topic> findByName(String name); // Find a topic by name
    List<Topic> findBySectionId(String sectionId); // Find all topics under a specific section
}
