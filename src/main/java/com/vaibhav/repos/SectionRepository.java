package com.vaibhav.repos;

import com.vaibhav.data.dao.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends MongoRepository<Section, String> {
    Optional<Section> findByName(String name); // Find a section by name
}
