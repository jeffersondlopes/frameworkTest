package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.Post;
import br.com.framework.frameworkpost.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable Long userid, @RequestBody Post post) {
        return postsService.create(userid, post);
    }

    @PostMapping("/user/")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createJwt(@RequestBody Post post) {
        return postsService.create(post);
    }

    @DeleteMapping("/user/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createJwt(@PathVariable Long postId) {
        postsService.delete(postId);
    }

}
