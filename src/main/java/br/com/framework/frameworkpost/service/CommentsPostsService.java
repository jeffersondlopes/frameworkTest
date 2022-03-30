package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.CommentsPosts;
import br.com.framework.frameworkpost.domain.Post;
import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.repository.CommentsPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentsPostsService {

    private final PostsService posts;
    private final CommentsPostsRepository commentsPostsRepository ;
    private final SecurityService securityService;


    @Autowired
    public CommentsPostsService(PostsService posts, CommentsPostsRepository commentsPostsRepository, SecurityService securityService) {
        this.posts = posts;
        this.commentsPostsRepository = commentsPostsRepository;
        this.securityService = securityService;
    }

    @Transactional
    public void create(Long postId, CommentsPosts commentsPosts) {
        //Post post = this.posts.findById(postId);
        //commentsPosts.setPost(post);
        buildCommentPost(postId, commentsPosts);
        commentsPostsRepository.save(commentsPosts);
    }

    void buildCommentPost (Long postId, CommentsPosts commentsPosts) {
        Post post = this.posts.findById(postId);
        User user = securityService.getUser();
        commentsPosts.setUser(user);
        commentsPosts.setPost(post);
    }

}
