package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.CommentsPosts;
import br.com.framework.frameworkpost.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsPostsService {

    private final PostsService posts;

    @Autowired
    public CommentsPostsService(PostsService posts) {
        this.posts = posts;
    }

    public void create(Long postId, CommentsPosts commentsPosts) {
        Post post = this.posts.findById(postId);
    }
}
