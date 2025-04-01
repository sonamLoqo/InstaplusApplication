package com.springboot.instapulse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "posts")
public class Post {
    @Id
    private String postId;
    private String username;  // User who posted
    private String caption;  // Caption of the post
    private String imageUrl;  // Image or video URL
    private List<String> hashtags;
    private List<String> mentions;  // Users mentioned in the post

    private int likes;
    private int comments;
    private int shares;
    private double engagementRate;

    private String postType;
    // When the post was created
    private String timestamp;

}
