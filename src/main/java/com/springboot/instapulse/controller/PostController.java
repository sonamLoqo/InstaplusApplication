package com.springboot.instapulse.controller;

import com.springboot.instapulse.service.PostService;
import com.springboot.instapulse.model.Post;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")

public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {

        this.postService = postService;
    }
    @PostMapping
    public Post addPost(@RequestBody Post post) {

        return postService.savePost(post);
    }

    @GetMapping("/username/{username}")
    public List<Post> getPosts(@PathVariable String username) {

        return postService.getPostsByUsername(username);
    }

    @GetMapping("/{username}/engagement")
    public Map<String, Integer> getTotalEngagement(@PathVariable String username) {
        return postService.getTotalLikesAndComments(username);
    }

}

