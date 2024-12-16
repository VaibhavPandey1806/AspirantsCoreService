package com.vaibhav.service;


import com.vaibhav.data.dao.*;
import com.vaibhav.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    @Autowired
    private QuestionPendingRepository questionPendingRepository;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private TopicRepository topicRepository; //
    
    @Autowired
    private UserService userService; 
    

    // Method to get questions based on category and topic
    public List<Question> getQuestionsByCategoryAndTopic(List<String> categories, List<String> topics) {
        return questionRepository.findBySectionInAndTopicIn(categories, topics);
    }
    
    
    public ResponseEntity<QuestionPending> addQuestion(QuestionPending question) {
        // Variables to hold section, topic, and source entities
        Section section = null;
        Topic topic = null;
        Source source = null;

        // Step 1: Check or create Section
        if (question.getSectionId() != null) {
            section = sectionRepository.findById(question.getSectionId()).orElse(null);
        }
        if (section == null && question.getSection() != null && !question.getSection().isEmpty()) {
            section = sectionRepository.findByName(question.getSection()).orElse(null);
            if (section == null) {
                section = new Section();
                section.setName(question.getSection());
                section = sectionRepository.save(section); // Save new section
            }
            question.setSectionId(section.getId());
        }

        // Step 2: Check or create Topic
        if (question.getTopicId() != null) {
            topic = topicRepository.findById(question.getTopicId()).orElse(null);
        }
        if (topic == null && question.getTopic() != null && !question.getTopic().isEmpty()) {
            topic = topicRepository.findByName(question.getTopic()).orElse(null);
            if (topic == null) {
                topic = new Topic();
                topic.setName(question.getTopic());
                topic.setSectionId(section != null ? section.getId() : null); // Link to section
                topic = topicRepository.save(topic); // Save new topic
            }
            question.setTopicId(topic.getId());
        }

        // Step 3: Check or create Source
        if (question.getSourceId() != null) {
            source = sourceRepository.findById(question.getSourceId()).orElse(null);
        }
        if (source == null && question.getSource() != null && !question.getSource().isEmpty()) {
            source = sourceRepository.findByName(question.getSource()).orElse(null);
            if (source == null) {
                source = new Source();
                source.setName(question.getSource());
                source = sourceRepository.save(source); // Save new source
            }
            question.setSourceId(source.getId());
        }

        // Set additional question details
        question.setDateTimeSubmitted(LocalDateTime.now());
        question.setSubmittedBy(userService.getUser().getId());
        question.setApproved(false);
        question.setRejected(false);

        // Step 4: Save the question with the updated references
        QuestionPending savedQuestion = questionPendingRepository.save(question);

        return ResponseEntity.ok(savedQuestion);
    }


	public List<Question> getQuestionsByFilters(List<String> category, List<String> topic, List<String> source) 
	{
		// TODO Auto-generated method stub
        return questionRepository.findByFilters(category, topic,source);
	}
}
