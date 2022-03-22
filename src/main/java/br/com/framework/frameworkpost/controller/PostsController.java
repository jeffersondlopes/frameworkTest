package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.Posts;
import br.com.framework.frameworkpost.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/user/{userid}")
    public List<Posts> listAll(@PathVariable Long userid){
        return postsService.listAll(userid);
    }

    @PostMapping("/user/{userid}")
    public Posts create(@PathVariable Long userid, @RequestBody Posts posts) {
        return postsService.create(userid, posts);
    }

}
