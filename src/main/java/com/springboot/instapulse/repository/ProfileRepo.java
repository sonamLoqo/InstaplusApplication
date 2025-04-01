package com.springboot.instapulse.repository;
import com.springboot.instapulse.model.Profile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepo extends ElasticsearchRepository<Profile, String> {
    List<Profile> findByUsername(String username);

    List<Profile> findByIsVerified(boolean isVerified);

    List<Profile> findByIsBusinessAccount(boolean isBusinessAccount);

    List<Profile> findByCategory(String category);

    List<Profile> findByHashtagsContaining(String keyword);

}
