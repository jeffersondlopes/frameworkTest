package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.CommentsPosts;
import br.com.framework.frameworkpost.service.CommentsPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments-post")
public class CommentPostController {

    private final CommentsPostsService commentsPostsService;

    @Autowired
    public CommentPostController(CommentsPostsService commentsPostsService) {
        this.commentsPostsService = commentsPostsService;
    }

    @PostMapping("/post/{postId}")
    private void create(@PathVariable Long postId, CommentsPosts commentsPosts) {
        commentsPostsService.create(postId, commentsPosts);
    }

}
