package com.springboot.instapulse.repository;

import com.springboot.instapulse.model.Profile;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProfileRepo extends ElasticsearchRepository<Profile, String> {

    List<Profile> findByUsername(String username);

    List<Profile> findByIsVerified(boolean isVerified);

    List<Profile> findByIsBusinessAccount(boolean isBusinessAccount);

    List<Profile> findByCategory(String category);

    List<Profile> findByHashtagsContaining(String keyword);

    @Query("{\"aggs\": {\"unique_hashtags\": {\"terms\": {\"field\": \"hashtags\",\"size\": 100}}}}")
    Map<String, Object> findUniqueHashtags();

    @Query("""
        {
            "wildcard": {
                "username.keyword": {
                    "value": "*?0*"
                }
            }
        }
    """)
    List<Profile> searchByUsernameContains(String keyword);

    @Query("""
        {
            "prefix": {
                "username.keyword": "?0"
            }
        }
    """)
    List<Profile> searchByUsernamePrefix(String keyword);

    @Query("""
        {
            "size": 10,
            "query": {
                "match_all": {}
            },
            "aggs": {
                "unique_categories": {
                    "terms": {
                        "field": "category.keyword",
                        "size": 100
                    }
                }
            }
        }
    """)
    Map<String, Object> findUniqueCategories();

    @Query("""
        {
            "query": {
                "range": {
                    "followersCount": {
                        "gte": 1000,
                        "lte": 10000
                    }
                }
            }
        }
    """)
    List<Profile> findByFollowerCountRange();

    @Query("""
        {
            "prefix": {
                "hashtags.keyword": "?0"
            }
        }
    """)
    List<Profile> searchByHashtagPrefix(String hashtagPrefix);


   @Query("{\"match\": {\"bio\": \"?0\"}}")
   List<Profile> searchByBioContains(String keyword);

    @Query("""
        {
            "query": {
                "match": {
                    "fullName": "?0"
                }
            }
        }
    """)
    List<Profile> searchByFullNameContains(String keyword);
}
