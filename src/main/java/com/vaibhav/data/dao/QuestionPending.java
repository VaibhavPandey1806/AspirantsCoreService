package com.vaibhav.data.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "questionsPending")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPending {
    @Id
    private String id; // MongoDB ID

    private String sectionId;      // ID of the section
    private String section;        // Name of the section
    private String topicId;        // ID of the topic
    private String topic;          // Name of the topic
    private String source;
    private String SourceId;// Source of the question
    private String questionText;   // Question text

    private String optionA;        // Option A
    private String optionB;        // Option B
    private String optionC;        // Option C
    private String optionD;        // Option D

    private String correctAnswer;  // Correct answer

    private String submittedBy;
    private Boolean approved;
    private Boolean rejected;
    private List<String> comments;// User who submitted the question

    public List<String> getComments() {
        return comments;
    }
    public void setComments(List<String> comments) {
        this.comments = comments;
    }
    public String getSourceId() {
        return SourceId;
    }
    public void setSourceId(String sourceId) {
        SourceId = sourceId;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")

    private LocalDateTime dateTimeSubmitted;  // Date and time the question was submitted
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSectionId() {
        return sectionId;
    }
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public String getTopicId() {
        return topicId;
    }
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getQuestionText() {
        return questionText;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public String getOptionA() {
        return optionA;
    }
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    public String getOptionB() {
        return optionB;
    }
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    public String getOptionC() {
        return optionC;
    }
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public String getOptionD() {
        return optionD;
    }
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public String getSubmittedBy() {
        return submittedBy;
    }
    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
    public LocalDateTime getDateTimeSubmitted() {
        return dateTimeSubmitted;
    }
    public void setDateTimeSubmitted(LocalDateTime dateTimeSubmitted) {
        this.dateTimeSubmitted = dateTimeSubmitted;
    }

    // Getters and Setters for all fields (if needed, Lombok's @Data generates these by default)
}
