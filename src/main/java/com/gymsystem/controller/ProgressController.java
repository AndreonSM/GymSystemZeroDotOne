package com.gymsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gymsystem.exceptions.ResourceNotFoundException;
import com.gymsystem.model.Progress;
import com.gymsystem.model.User;
import com.gymsystem.service.ProgressService;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping
    public List<Progress> getAllProgress() {
        return progressService.getAllProgress();
    }

    @GetMapping("/{progressId}")
    public ResponseEntity<Progress> getProgressById(@PathVariable Long progressId) {
        try {
            Progress progress = progressService.getProgressById(progressId);
            return new ResponseEntity<>(progress, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Progress> getProgressByUser(@PathVariable Long userId) {
        try {
            User user = new User();
            user.setId(userId);
            return progressService.getProgressByUser(user);
        } catch (ResourceNotFoundException e) {
            return new ArrayList<>(); // or handle as needed
        }
    }

    @PostMapping
    public ResponseEntity<Progress> createProgress(@RequestBody Progress progress) {
        Progress createdProgress = progressService.createProgress(progress);
        return new ResponseEntity<>(createdProgress, HttpStatus.CREATED);
    }

    @PutMapping("/{progressId}")
    public ResponseEntity<Progress> updateProgress(@PathVariable Long progressId, @RequestBody Progress updatedProgress) {
        try {
            Progress progress = progressService.updateProgress(progressId, updatedProgress);
            return new ResponseEntity<>(progress, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{progressId}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long progressId) {
        try {
            progressService.deleteProgress(progressId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
