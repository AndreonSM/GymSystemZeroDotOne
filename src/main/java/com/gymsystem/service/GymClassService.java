package com.gymsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.exceptions.ResourceNotFoundException;
import com.gymsystem.model.GymClass;
import com.gymsystem.repository.GymClassRepository;

@Service
public class GymClassService {

    @Autowired
    private GymClassRepository gymClassRepository;

    public List<GymClass> getAllClasses() {
        return gymClassRepository.findAll();
    }

    public GymClass getClassById(Long classId) {
        return gymClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("GymClass", "id", classId));
    }

    public GymClass createClass(GymClass gymClass) {
        // You might want to perform additional validation before saving
        return gymClassRepository.save(gymClass);
    }

    public GymClass updateClass(Long classId, GymClass updatedClass) {
        GymClass existingClass = gymClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("GymClass", "id", classId));

        // Update only the fields you want to allow
        existingClass.setName(updatedClass.getName());
        existingClass.setSchedule(updatedClass.getSchedule());
        existingClass.setMaxCapacity(updatedClass.getMaxCapacity());
        // Update other fields as needed

        return gymClassRepository.save(existingClass);
    }

    public void deleteClass(Long classId) {
        if (gymClassRepository.existsById(classId)) {
            gymClassRepository.deleteById(classId);
        } else {
            throw new ResourceNotFoundException("GymClass", "id", classId);
        }
    }
}
