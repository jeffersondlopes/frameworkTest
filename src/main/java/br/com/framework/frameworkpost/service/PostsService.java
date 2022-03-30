package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.Post;
import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.domain.excpeiton.NotFoundException;
import br.com.framework.frameworkpost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostsService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final SecurityService securityService;

    private static final String POST_NOT_FOUD = "Post %d não encontrado ou desativado";
    private static final String POST_NOT_FOUD_BY_OWNER = "Post %d não pertence ao usuário %s";

    @Autowired
    public PostsService(UserService userService, PostRepository postRepository, SecurityService securityService) {
        this.userService = userService;
        this.postRepository = postRepository;
        this.securityService = securityService;
    }

    @Transactional
    public Post create(Long userId, Post post) {
        User user = userService.findById(userId);
        post.setUser(user);
        return postRepository.save(post);
    }

    @Transactional
    public Post create(Post post) {
        User user = userService.findByEmail(securityService.getUserIdJwt());
        post.setUser(user);
        return postRepository.save(post);
    }

    @Transactional
    public void delete(Long postId) {
        User user = userService.findByEmail(securityService.getUserIdJwt());
        Optional<Post> optionalPost = checkOwerPost(postId, user.getEmail());
        optionalPost.orElseThrow(() -> new NotFoundException(String.format(POST_NOT_FOUD,postId)));
        Post post = optionalPost.get();
        post.setActive(false);
    }

    public List<Post> listAll(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.format(POST_NOT_FOUD,postId)));
    }

    Optional<Post> checkOwerPost(Long postId, String email) {
        return postRepository.findPostByOwnerEmail(postId, email);
    }

}
