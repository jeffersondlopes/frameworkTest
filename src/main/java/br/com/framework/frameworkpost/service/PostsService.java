package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.Post;
import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostsService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final SecurityService securityService;

    private static final String POST_NOT_FOUD = "Post %d não encontrado.";

    @Autowired
    public PostsService(UserService userService, PostRepository postRepository, SecurityService securityService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.securityService = securityService;
    }

    @Transactional
    public Post create(Long userId, Post post) {
        User user = userService.findById(userId);
        post.setUsers(user);
        return postRepository.save(post);
    }

    @Transactional
    public Post create(Post post) {
        User user = userService.findByEmail(securityService.getUserIdJwt());
        post.setUsers(user);
        return postRepository.save(post);
    }

    public List<Post> listAll(Long userId) {
        return postRepository.findAllByUsersId(userId);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException(String.format(POST_NOT_FOUD,postId)));
    }
}
