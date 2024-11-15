
package com.vaibhav.repos;

import com.vaibhav.data.dao.Source;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends MongoRepository<Source, String> {
    Optional<Source> findByName(String name); // Find a section by name
}
