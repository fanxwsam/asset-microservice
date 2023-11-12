package com.asset.obs.integration;

import com.asset.obs.common.exception.SystemException;
import com.asset.obs.model.AuditionPost;
import com.asset.obs.model.AuditionPostComment;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditionIntegrationClient {

    private final RestTemplate restTemplate;
    private static final String JSON_PLACEHOLDER_BASE_URL = "https://jsonplaceholder.typicode.com";

    public AuditionIntegrationClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Make RestTemplate call to get Posts from https://jsonplaceholder.typicode.com/posts
     *
     * @return List List of AuditionPost without comments
     */
    public List<AuditionPost> getPosts() {
        final String requestURL = JSON_PLACEHOLDER_BASE_URL + "/posts";
        final AuditionPost[] auditionPosts = restTemplate.getForObject(requestURL, AuditionPost[].class);
        return Arrays.asList(auditionPosts);
    }


    /**
     * Get post by post ID call from https://jsonplaceholder.typicode.com/posts/{id}
     *
     * @param id postId
     * @return AuditionPost post without comments
     */
    public AuditionPost getPostById(final String id) {
        try {
            final String requestURL = JSON_PLACEHOLDER_BASE_URL + "/posts/" + id;
            return restTemplate.getForObject(requestURL, AuditionPost.class);

        } catch (final HttpClientErrorException.NotFound e) {
            throw new SystemException("Cannot find a Post with id " + id, "Resource Not Found",
                404, e);
        } catch (HttpClientErrorException.BadRequest e) {
            // Handle other HttpClientErrorException (e.g., 4xx status codes) here
            throw new SystemException("Query post with id " + id + " failed", "Client Error", 400, e);
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx status codes) here
            throw new SystemException("Query post with id " + id + " failed", "Server Error", 400, e);
        }
    }

    /**
     * GET comments for a post from https://jsonplaceholder.typicode.com/posts/{postId}/comments Get post by calling
     * existing method getPostById Form the structure of 'post' which 'comments' are contained by the post
     *
     * @param postId will be used in the url
     * @return 'post' with 'comments' inside
     */
    public AuditionPost getPostWithCommentsByPostId(final String postId) {
        final String requestURL = JSON_PLACEHOLDER_BASE_URL + "/posts/" + postId + "/comments";

        final AuditionPostComment[] auditionPostComments = restTemplate.getForObject(requestURL,
            AuditionPostComment[].class);

        // Call existing method 'getPostById' to create object auditionPost
        final AuditionPost auditionPost = getPostById(postId);

        // Set comment list for post
        auditionPost.setAuditionPostComments(Arrays.asList(auditionPostComments));

        return auditionPost;
    }


    /**
     * GET comments for a particular Post from https://jsonplaceholder.typicode.com/comments?postId={postId}.
     * The comments are a separate list that needs to be returned to the API consumers. Hint: this is not part of the
     * AuditionPost pojo.
     *
     * @param postId will be used in the url
     * @return comment list.
     */
    public List<AuditionPostComment> getCommentsByPostId(final String postId) {
        final String requestURL = JSON_PLACEHOLDER_BASE_URL + "/comments?postId=" + postId;
        final AuditionPostComment[] auditionPostComments = restTemplate.getForObject(requestURL,
            AuditionPostComment[].class);

        return Arrays.asList(auditionPostComments);
    }
}
