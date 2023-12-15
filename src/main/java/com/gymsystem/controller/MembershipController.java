package com.gymsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gymsystem.exceptions.ResourceNotFoundException;
import com.gymsystem.model.Membership;
import com.gymsystem.model.User;
import com.gymsystem.service.MembershipService;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @GetMapping
    public List<Membership> getAllMemberships() {
        return membershipService.getAllMemberships();
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<Membership> getMembershipById(@PathVariable Long membershipId) {
        try {
            Membership membership = membershipService.getMembershipById(membershipId);
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public List<Membership> getMembershipsByUser(@PathVariable Long userId) {
        try {
            User user = new User();
            user.setId(userId);
            return membershipService.getMembershipsByUser(user);
        } catch (ResourceNotFoundException e) {
            return new ArrayList<>(); // or handle as needed
        }
    }

    @PostMapping
    public ResponseEntity<Membership> createMembership(@RequestBody Membership membership) {
        Membership createdMembership = membershipService.createMembership(membership);
        return new ResponseEntity<>(createdMembership, HttpStatus.CREATED);
    }

    @PutMapping("/{membershipId}")
    public ResponseEntity<Membership> updateMembership(@PathVariable Long membershipId, @RequestBody Membership updatedMembership) {
        try {
            Membership membership = membershipService.updateMembership(membershipId, updatedMembership);
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{membershipId}")
    public ResponseEntity<Void> deleteMembership(@PathVariable Long membershipId) {
        try {
            membershipService.deleteMembership(membershipId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
