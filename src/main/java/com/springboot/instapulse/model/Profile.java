package com.springboot.instapulse.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "profile")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Profile {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String username;

    @Field(type = FieldType.Text)
    private String fullName;

    @Field(type = FieldType.Text)
    private String bio;

    @Field(type = FieldType.Text)
    private String category;

    @Field(type = FieldType.Text)
    private String profilePictureUrl;

    @Field(type = FieldType.Text)
    private String website;

    @Field(type = FieldType.Boolean)
    private boolean isVerified;

    @Field(type = FieldType.Boolean)
    private boolean isBusinessAccount;

    @Field(type = FieldType.Integer)
    private int followersCount;

    @Field(type = FieldType.Integer)
    private int followingCount;

    @Field(type = FieldType.Integer)
    private int totalPosts;

    @Field(type = FieldType.Integer)
    private int totalReels;

    @Field(type = FieldType.Integer)
    private int totalStories;

    @Field(type = FieldType.Double)
    private double engagementRate;

    @Field(type = FieldType.Boolean)
    private boolean hasHighlights;

    @Field(type = FieldType.Boolean)
    private boolean isPrivate;


    @Field(type = FieldType.Keyword)
    @JsonProperty("hashtagsUsed")
    private List<String> hashtags;

    public List<String> getHashtags() {
        return hashtags;
    }
}
