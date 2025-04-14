package com.springboot.instapulse.service;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.springboot.instapulse.model.Profile;
import com.springboot.instapulse.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service

public class ProfileService {
    private final ProfileRepo profileRepo;
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    public ProfileService(ProfileRepo profileRepo) {

        this.profileRepo = profileRepo;
    }

    public List<Profile> getAllProfiles() {

        List<Profile> profiles = new ArrayList<>();
        profileRepo.findAll().forEach(profiles::add);
        System.out.println("Profiles found: " + profiles.size());
        return profiles;
    }

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

    public List<String> getUniqueHashtags() {
        try {
            var response = elasticsearchClient.search(s -> s
                            .index("profile")
                            .size(0)
                            .aggregations("unique_hashtags", a -> a
                                    .terms(t -> t.field("hashtags").size(100))),
                    Void.class);

            return response.aggregations()
                    .get("unique_hashtags")
                    .sterms()
                    .buckets()
                    .array()
                    .stream()
                    .map(bucket -> bucket.key().stringValue())
                    .sorted() // Alphabetical sort
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Failed to query Elasticsearch", e);
        }
    }


    public List<Profile>searchByUsernameContains(String keyword) {
        if(keyword.length()<3)
            return Collections.emptyList();
        return profileRepo.searchByUsernameContains(keyword);
    }

    public List<Profile>searchByUsernamePrefix(String prefix){
        if(prefix.length()<3)
            return Collections.emptyList();
        return profileRepo.searchByUsernamePrefix(prefix);
    }

    public List<String>getUsernamesByContain(String keyword){
        return profileRepo.searchByUsernameContains(keyword)
                .stream()
                .map(Profile::getUsername)
                .collect(Collectors.toList());
    }

    public List<String>getUsernamesByPrefix(String prefix){
        return profileRepo.searchByUsernamePrefix(prefix)
                .stream()
                .map(Profile::getUsername)
                .collect(Collectors.toList());
    }

    public List<String> getUniqueCategoryList() {
        Map<String, Object> aggregationResult = profileRepo.findUniqueCategories();

        if (aggregationResult == null || !aggregationResult.containsKey("aggregations")) {
            return Collections.emptyList();
        }

        Map<String, Object> aggregations = (Map<String, Object>) aggregationResult.get("aggregations");
        Map<String, Object> uniqueCategories = (Map<String, Object>) aggregations.get("unique_categories");

        if (uniqueCategories == null || !uniqueCategories.containsKey("buckets")) {
            return Collections.emptyList();
        }

        List<Map<String, Object>> buckets = (List<Map<String, Object>>) uniqueCategories.get("buckets");

        List<String> categoryNames = new ArrayList<>();
        for (Map<String, Object> bucket : buckets) {
            Object key = bucket.get("key");
            if (key instanceof String) {
                categoryNames.add((String) key);
            }
        }

        return categoryNames;
    }



    public List<Profile> searchByBioContains(String keyword) {
        return profileRepo.searchByBioContains(keyword);
    }

    public List<Profile> searchByFullNameContains(String keyword) {
        return profileRepo.searchByFullNameContains(keyword);
    }


}
