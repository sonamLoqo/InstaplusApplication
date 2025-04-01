package com.springboot.instapulse.controller;

import com.springboot.instapulse.Service.PostService;
import com.springboot.instapulse.model.Post;

import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/{username}")
    public List<Post> getPosts(@PathVariable String username) {
        return postService.getPostsByUsername(username);
    }
}

