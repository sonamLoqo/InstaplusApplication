package com.springboot.instapulse.controller;

import com.springboot.instapulse.model.Profile;
import com.springboot.instapulse.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Profile> getProfilesByCategory(@PathVariable String category) {
        return profileService.searchByCategory(category);
    }

    @GetMapping("/hashtag/{hashtag}")
    public List<Profile> getProfilesByHashtag(@PathVariable String hashtag) {
        return profileService.searchByHashtag(hashtag);
    }

    @GetMapping("/hashtags/unique")
    public ResponseEntity<List<String>> getUniqueHashtags() {
        List<String> hashtags = profileService.getUniqueHashtags();
        return ResponseEntity.ok(hashtags);
    }

    @GetMapping("/search/username/contains")
    public List<Profile> searchByUsernameContains(@RequestParam String keyword) {
        return profileService.searchByUsernameContains(keyword);
    }

    @GetMapping("/search/username/prefix")
    public List<Profile> searchByUsernamePrefix(@RequestParam String prefix) {
        return profileService.searchByUsernamePrefix(prefix);
    }

    @GetMapping("/usernames/contain")
    public List<String> getUsernamesByContain(@RequestParam String keyword) {
        return profileService.getUsernamesByContain(keyword);
    }

    @GetMapping("/usernames/prefix")
    public List<String> getUsernamesByPrefix(@RequestParam String prefix) {
        return profileService.getUsernamesByPrefix(prefix);
    }


    @GetMapping("/uniqueCategories")
    public ResponseEntity<List<String>> getUniqueCategories() {
        return ResponseEntity.ok(profileService.getUniqueCategoryList());
    }


    @GetMapping("/searchByBio")
    public List<Profile> searchByBio(@RequestParam String keyword) {
        return profileService.searchByBioContains(keyword);
    }

    @GetMapping("/searchByFullName")
    public List<Profile> searchByFullName(@RequestParam String keyword) {
        return profileService.searchByFullNameContains(keyword);
    }

}
