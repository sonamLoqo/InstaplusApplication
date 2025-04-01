package com.springboot.instapulse.controller;
import com.springboot.instapulse.model.Profile;
import com.springboot.instapulse.Service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/profiles")
@RestController

public class ProfileController {
    private final ProfileService profileService;
    public ProfileController(ProfileService profileService) {

        this.profileService = profileService;
    }
    @PostMapping
    public ResponseEntity<?> addProfiles(@RequestBody List<Profile> profiles) {
        try {
            List<Profile> savedProfiles = profileService.saveProfiles(profiles);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProfiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving profiles: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping("/{username}")
    public List<Profile> getProfiles(@PathVariable String username) {
        return profileService.searchByUserName(username);
    }

    @GetMapping("/verified")
    public List<Profile> getVerifiedProfiles() {

        return profileService.getVerifiedProfiles();
    }

    @GetMapping("/business")
    public List<Profile> getBusinessProfiles() {

        return profileService.getBusinessProfiles();
    }

    @GetMapping("/category/{category}")
    public List<Profile>getProfilesByCategory(@PathVariable String category) {
        return profileService.searchByCategory(category);
    }

    @GetMapping("/hashtag/{hashtag}")
    public List<Profile> getProfilesByHashtag(@PathVariable String hashtag) {
        return profileService.searchByHashtag(hashtag);
    }
    @GetMapping("/unique_hashtags")
    public Set<String> getUniqueHashtags() {
        return profileService.getUniqueHashtags();
    }

}
