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
    private String username;
    private String caption;
    private String imageUrl;
    private List<String> hashtags;
    private List<String> mentions;

    private int likes;
    private int comments;
    private int shares;
    private double engagementRate;

    private String postType;
    // When the post was created
    private String timestamp;

}
