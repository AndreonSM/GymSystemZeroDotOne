package com.gymsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gymsystem.exceptions.ResourceNotFoundException;
import com.gymsystem.model.GymClass;
import com.gymsystem.service.GymClassService;

@RestController
@RequestMapping("/api/classes")
public class GymClassController {

    @Autowired
    private GymClassService gymClassService;

    @GetMapping
    public List<GymClass> getAllClasses() {
        return gymClassService.getAllClasses();
    }

    @GetMapping("/{classId}")
    public ResponseEntity<GymClass> getClassById(@PathVariable Long classId) {
        try {
            GymClass gymClass = gymClassService.getClassById(classId);
            return new ResponseEntity<>(gymClass, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<GymClass> createClass(@RequestBody GymClass gymClass) {
        GymClass createdClass = gymClassService.createClass(gymClass);
        return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<GymClass> updateClass(@PathVariable Long classId, @RequestBody GymClass updatedClass) {
        try {
            GymClass gymClass = gymClassService.updateClass(classId, updatedClass);
            return new ResponseEntity<>(gymClass, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long classId) {
        try {
            gymClassService.deleteClass(classId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
