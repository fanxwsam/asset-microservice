package com.asset.obs.service;

import com.asset.obs.integration.AuditionIntegrationClient;
import com.asset.obs.model.AuditionPost;
import com.asset.obs.model.AuditionPostComment;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuditionService {
    private final AuditionIntegrationClient auditionIntegrationClient;

    public AuditionService(final AuditionIntegrationClient auditionIntegrationClient) {
        this.auditionIntegrationClient = auditionIntegrationClient;
    }

    public List<AuditionPost> getPosts() {
        return auditionIntegrationClient.getPosts();
    }

    public AuditionPost getPostById(final String postId) {
        return auditionIntegrationClient.getPostById(postId);
    }

    public AuditionPost getPostWithCommentsByPostId(final String postId) {
        return auditionIntegrationClient.getPostWithCommentsByPostId(postId);
    }

    public List<AuditionPostComment> getCommentsByPostId(final String postId) {
        return auditionIntegrationClient.getCommentsByPostId(postId);
    }

}
