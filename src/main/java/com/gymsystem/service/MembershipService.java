package com.gymsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsystem.exceptions.ResourceNotFoundException;
import com.gymsystem.model.Membership;
import com.gymsystem.model.User;
import com.gymsystem.repository.MembershipRepository;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public Membership getMembershipById(Long membershipId) {
        return membershipRepository.findById(membershipId)
                .orElseThrow(() -> new ResourceNotFoundException("Membership", "id", membershipId));
    }

    public List<Membership> getMembershipsByUser(User user) {
        return membershipRepository.findByUser(user);
    }

    public Membership createMembership(Membership membership) {
        // You might want to perform additional validation before saving
        return membershipRepository.save(membership);
    }

    public Membership updateMembership(Long membershipId, Membership updatedMembership) {
        Membership existingMembership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new ResourceNotFoundException("Membership", "id", membershipId));

        // Update only the fields you want to allow
        existingMembership.setType(updatedMembership.getType());
        existingMembership.setStartDate(updatedMembership.getStartDate());
        existingMembership.setEndDate(updatedMembership.getEndDate());
        // Update other fields as needed

        return membershipRepository.save(existingMembership);
    }

    public void deleteMembership(Long membershipId) {
        if (membershipRepository.existsById(membershipId)) {
            membershipRepository.deleteById(membershipId);
        } else {
            throw new ResourceNotFoundException("Membership", "id", membershipId);
        }
    }
}
