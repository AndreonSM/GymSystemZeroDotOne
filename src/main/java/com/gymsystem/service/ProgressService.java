package com.gymsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.exceptions.ResourceNotFoundException;
import com.gymsystem.model.Progress;
import com.gymsystem.model.User;
import com.gymsystem.repository.ProgressRepository;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }

    public Progress getProgressById(Long progressId) {
        return progressRepository.findById(progressId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress", "id", progressId));
    }

    public List<Progress> getProgressByUser(User user) {
        return progressRepository.findByUser(user);
    }

    public Progress createProgress(Progress progress) {
        // You might want to perform additional validation before saving
        return progressRepository.save(progress);
    }

    public Progress updateProgress(Long progressId, Progress updatedProgress) {
        Progress existingProgress = progressRepository.findById(progressId)
                .orElseThrow(() -> new ResourceNotFoundException("Progress", "id", progressId));

        // Update only the fields you want to allow
        existingProgress.setWeight(updatedProgress.getWeight());
        existingProgress.setBodyMeasurements(updatedProgress.getBodyMeasurements());
        // Update other fields as needed

        return progressRepository.save(existingProgress);
    }

    public void deleteProgress(Long progressId) {
        if (progressRepository.existsById(progressId)) {
            progressRepository.deleteById(progressId);
        } else {
            throw new ResourceNotFoundException("Progress", "id", progressId);
        }
    }
}
