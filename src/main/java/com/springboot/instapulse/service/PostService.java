package com.springboot.instapulse.service;

import com.springboot.instapulse.repository.PostRepo;
import com.springboot.instapulse.model.Post;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class PostService {
    private final PostRepo postRepo;
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Post savePost(Post post) {
        return postRepo.save(post);
    }
    public List<Post> getPostsByUsername(String userName) {

        return postRepo.findByUsername(userName);
    }

    public Map<String, Integer> getTotalLikesAndComments(String username) {
        List<Post> posts = postRepo.findByUsername(username);

        int totalLikes = posts.stream().mapToInt(Post::getLikes).sum();
        int totalComments = posts.stream().mapToInt(Post::getComments).sum();

        Map<String, Integer> result = new HashMap<>();
        result.put("totalLikes", totalLikes);
        result.put("totalComments", totalComments);

        return result;
    }
}
