package com.springboot.instapulse.Service;
import com.springboot.instapulse.model.Profile;
import com.springboot.instapulse.repository.ProfileRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service

public class ProfileService {
    private final ProfileRepo profileRepo;

    public ProfileService(ProfileRepo profileRepo) {

        this.profileRepo = profileRepo;
    }

    public List<Profile> getAllProfiles() {
        //Here I convert Iterable to list
        List<Profile> profiles = new ArrayList<>();
        profileRepo.findAll().forEach(profiles::add);
        System.out.println("Profiles found: " + profiles.size());
        return profiles;
    }

    /*public List<Profile> saveProfiles(List<Profile> profiles) {
        return (List<Profile>) profileRepo.saveAll(profiles);
    }*/
    public List<Profile> saveProfiles(List<Profile> profiles) {
        System.out.println("Received profiles for saving: " + profiles);
        List<Profile> savedProfiles = (List<Profile>) profileRepo.saveAll(profiles);
        System.out.println("Saved profiles in Elasticsearch: " + savedProfiles);
        return savedProfiles;
    }
    public List<Profile>searchByUserName(String username) {

        return profileRepo.findByUsername(username);
    }
    public List<Profile>getVerifiedProfiles() {

        return profileRepo.findByIsVerified(true);
    }
    public List<Profile>getBusinessProfiles() {

        return profileRepo.findByIsBusinessAccount(true);
    }
    public List<Profile>searchByCategory(String category) {

        return profileRepo.findByCategory(category);
    }
    public List<Profile> searchByHashtag(String hashtag) {

        return profileRepo.findByHashtagsContaining(hashtag);
    }

    public Set<String> getUniqueHashtags() {
        List<Profile>profiles =getAllProfiles();
        Set<String>uniqueHashtags =new HashSet<>();
        for(Profile profile:profiles) {
            if(profile.getHashtags()!=null) {
                uniqueHashtags.addAll(profile.getHashtags());
            }
        }
        return uniqueHashtags;
    }
}
