package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.CommentsPosts;
import br.com.framework.frameworkpost.service.CommentsPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentPostController {

    private final CommentsPostsService commentsPostsService;

    @Autowired
    public CommentPostController(CommentsPostsService commentsPostsService) {
        this.commentsPostsService = commentsPostsService;
    }

    @PostMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    private CommentsPosts create(@PathVariable Long postId, @RequestBody CommentsPosts commentsPosts) {
        commentsPostsService.create(postId, commentsPosts);
        return commentsPosts;
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteComment(@PathVariable Long commentId) {
        commentsPostsService.deleteById(commentId);
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    private List<CommentsPosts> listAllCommentsByPostId(@PathVariable Long postId) {
        return commentsPostsService.findAllByPostId(postId);
    }

}
