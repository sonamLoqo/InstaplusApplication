package com.springboot.instapulse.repository;

import com.springboot.instapulse.model.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends ElasticsearchRepository<Post, String> {
    List<Post> findByUsername(String username);
}
