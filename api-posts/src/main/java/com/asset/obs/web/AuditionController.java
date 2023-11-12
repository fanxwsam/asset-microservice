package com.asset.obs.web;

import com.asset.obs.common.exception.InvalidQueryInputException;
import com.asset.obs.model.AuditionPost;
import com.asset.obs.model.AuditionPostComment;
import com.asset.obs.service.AuditionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "AuditionController", description = "Audition management APIs")
@RestController
public class AuditionController {

    private final AuditionService auditionService;
    private static final String CONTROLLER_NAME = "AuditionController";
    private static final String REQUEST_METHOD = "GET";

    public AuditionController(final AuditionService auditionService) {
        this.auditionService = auditionService;
    }


    /**
     * The endpoint will check if query param 'filter' exists.
     * If exists, return the filtered list It will check any of the 4 fields userId, id, title and body contain
     * the value of param 'filter' Simultaneously perform a fuzzy search
     * on four fields e.g. if "filter=12", as long as any of the 4 fields userId, id, title and body contain "12",
     * the post will be in the list
     *
     * @param filter the query criteria
     * @return List
     */
    @Operation(
        summary = "Get an AuditionPost list",
        description = "It will check if query param 'filter' exists, if exists, return the filtered list. \n\n "
            + "Param 'filter' can be null, which means return all the posts. \n\n"
            + " If any of the 4 fields userId, id, title and body contains the value of param 'filter', the post will be in the post list.",
        tags = {CONTROLLER_NAME, REQUEST_METHOD})
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuditionPost> getPosts(final @RequestParam(value = "filter", required = false) String filter) {

        final List<AuditionPost> posts = auditionService.getPosts();

        // if param 'filter' does not exist, return the original post list. Happy path.
        if (filter == null || filter.isBlank()) {
            return posts;
        }

        // Filter response data based on the query param
        return posts.stream()
            .filter(
                post -> String.valueOf(post.getId()).contains(filter)
                    || String.valueOf(post.getUserId()).contains(filter)
                    || post.getTitle().contains(filter)
                    || post.getBody().contains(filter)
            )
            .toList();
    }

    /**
     * Get a post by Id.
     *
     * @param postId id in the url
     * @return AuditionPost
     */
    @Operation(
        summary = "Get an AuditionPost by Id",
        description = "Retrieves a specific audition post by its 'id'. \n\n"
            + "Performs input validation to ensure the 'id' is a valid integer. ",
        tags = {CONTROLLER_NAME, REQUEST_METHOD})
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AuditionPost getPost(@PathVariable("id") final String postId) {
        // Input validation
        if (postId != null && postId.matches("\\d+")) {
            return auditionService.getPostById(postId);
        } else {
            throw new InvalidQueryInputException("Post id can only be an integer number : " + postId, 500);
        }
    }


    /**
     * Return comments for each post. Return AuditionPost along with its comments.
     *
     * @param postId id in the url
     * @return AuditionPost with comments inside
     */
    @Operation(
        summary = "Get Audition Post with Comments",
        description = "Retrieves an audition post along with its associated comments. \n\n"
            + " Performs input validation to ensure the postId is a valid integer.",
        tags = {CONTROLLER_NAME, REQUEST_METHOD})
    @RequestMapping(value = "/posts/{postId}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AuditionPost getPostWithComments(@PathVariable("postId") final String postId) {

        // Input validation
        if (postId != null && postId.matches("\\d+")) {
            return auditionService.getPostWithCommentsByPostId(postId);
        } else {
            throw new InvalidQueryInputException("Post id can only be an integer number : " + postId, 500);
        }
    }


    /**
     * Return a post's comments list. Query param is 'postId'.
     *
     * @param postId in the url
     * @return List of AuditionPostComment
     */
    @Operation(
        summary = "Get Comments by Post ID",
        description = "Retrieves a list of comments for a specific audition post based on the provided query parameter (postId). \n\n"
            + " Performs input validation to ensure the postId is a valid integer.",
        tags = {CONTROLLER_NAME, REQUEST_METHOD})
    @RequestMapping(value = "/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuditionPostComment> getCommentsByPostId(@RequestParam("postId") final String postId) {

        // Input validation
        if (postId != null && postId.matches("\\d+")) {
            return auditionService.getCommentsByPostId(postId);
        } else {
            throw new InvalidQueryInputException(
                "Post id cannot be null and the value only can be an integer number : " + postId, 500);
        }
    }
}