package com.vaibhav.service;

import com.vaibhav.data.dao.Question;
import com.vaibhav.data.dao.Section;
import com.vaibhav.data.dao.Topic;
import com.vaibhav.data.dao.Source;
import com.vaibhav.repos.QuestionRepository;
import com.vaibhav.repos.SectionRepository;
import com.vaibhav.repos.SourceRepository;
import com.vaibhav.repos.TopicRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Optional;

@Service
public class ExcelToMongoService {

    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private TopicRepository topicRepository;
    
    @Autowired
    private SourceRepository sourceRepository;

    public void importExcelData(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
            Iterator<Row> rows = sheet.iterator();

            // Skip header row
            if (rows.hasNext()) rows.next();

            while (rows.hasNext()) {
                Row row = rows.next();
                Question question = new Question();

                // Generate or fetch Section by name (if not already present)
                String sectionName = getCellValue(row, 0);
                Section section = sectionRepository.findByName(sectionName)
                        .orElseGet(() -> createNewSection(sectionName));

                // Generate or fetch Topic by name (if not already present)
                String topicName = getCellValue(row, 1);
                Topic topic = topicRepository.findByName(topicName)
                        .orElseGet(() -> createNewTopic(topicName, section.getId()));
                
                String sourceName = getCellValue(row, 2);
                Source source = sourceRepository.findByName(sourceName)
                        .orElseGet(() -> createNewSource(sourceName));


                // Set the IDs from the created or fetched Section and Topic
                question.setSectionId(section.getId());
                question.setSection(sectionName);
                question.setTopicId(topic.getId());
                question.setTopic(topicName);
                
                question.setSource(sourceName);
                question.setSourceId(source.getId());
                question.setQuestionText(getCellValue(row, 3));
                question.setOptionA(getCellValue(row, 4));
                question.setOptionB(getCellValue(row, 5));
                question.setOptionC(getCellValue(row, 6));
                question.setOptionD(getCellValue(row, 7));
                question.setCorrectAnswer(getCellValue(row, 8));
                question.setDateTimeSubmitted(LocalDateTime.now());
                question.setSubmittedBy("6740d6fb5343bb476c3b914e");

                // Save the question
                questionRepository.save(question);
            }

        }
    }

    private Section createNewSection(String sectionName) {
        Section newSection = new Section();
        newSection.setName(sectionName);
        newSection.setDetails("Details for " + sectionName); // Set details as per your requirement
        return sectionRepository.save(newSection);
    }
    
    private Source createNewSource(String sourceName) {
        Source newSource = new Source();
        newSource.setName(sourceName);
        return sourceRepository.save(newSource);
    }

    private Topic createNewTopic(String topicName, String sectionId) {
        Topic newTopic = new Topic();
        newTopic.setName(topicName);
        newTopic.setSectionId(sectionId);
        newTopic.setDetails("Details for " + topicName); // Set details as per your requirement
        return topicRepository.save(newTopic);
    }

    private String getCellValue(Row row, int cellIndex) {
        return row.getCell(cellIndex) != null ? row.getCell(cellIndex).toString().trim() : "";
    }
}
