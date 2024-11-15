package com.vaibhav.service;

import com.vaibhav.data.dao.Section;
import com.vaibhav.repos.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public Section findByName(String name) {
        return sectionRepository.findByName(name).orElse(null); // Fetch section by name
    }

    public Section createSection(Section section) {
        return sectionRepository.save(section); // Save the section to the database
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll(); // Fetch all sections from the repository
    }
}
