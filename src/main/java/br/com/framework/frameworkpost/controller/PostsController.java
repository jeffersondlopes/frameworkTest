package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.Posts;
import br.com.framework.frameworkpost.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping("/{userid}")
    public Posts create(@PathVariable Long userid, @RequestBody Posts posts) {
        return postsService.create(userid, posts);
    }

}
