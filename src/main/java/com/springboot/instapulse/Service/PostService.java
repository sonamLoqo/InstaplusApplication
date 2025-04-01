package com.springboot.instapulse.Service;

import com.springboot.instapulse.repository.PostRepo;
import com.springboot.instapulse.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
