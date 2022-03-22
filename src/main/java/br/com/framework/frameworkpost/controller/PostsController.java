package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.Post;
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
    public List<Post> listAll(@PathVariable Long userid){
        return postsService.listAll(userid);
    }

    @PostMapping("/user/{userid}")
    public Post create(@PathVariable Long userid, @RequestBody Post post) {
        return postsService.create(userid, post);
    }

}
