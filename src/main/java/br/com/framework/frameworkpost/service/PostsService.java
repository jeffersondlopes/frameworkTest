package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.Posts;
import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostsService {

    private final UserService userService;
    private final PostRepository postRepository;

    @Autowired
    public PostsService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    @Transactional
    public Posts create(Long userId, Posts posts) {
        User user = userService.findById(userId);
        posts.setUsers(user);
        return postRepository.save(posts);
    }


}
