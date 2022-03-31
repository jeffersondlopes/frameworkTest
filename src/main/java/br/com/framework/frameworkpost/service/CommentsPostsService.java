package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.CommentsPosts;
import br.com.framework.frameworkpost.domain.Post;
import br.com.framework.frameworkpost.domain.User;
import br.com.framework.frameworkpost.domain.excpeiton.BusinessException;
import br.com.framework.frameworkpost.domain.excpeiton.NotFoundException;
import br.com.framework.frameworkpost.repository.CommentsPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentsPostsService {

    private static final String COMMENT_BY_OWNER_NOT_FOUND = "Comentário %d não percente ao usuaŕio %s !";

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
        buildCommentPost(postId, commentsPosts);
        commentsPostsRepository.save(commentsPosts);
    }

    void buildCommentPost (Long postId, CommentsPosts commentsPosts) {
        Post post = this.posts.findById(postId);
        User user = securityService.getUser();
        commentsPosts.setUser(user);
        commentsPosts.setPost(post);
    }

    public List<CommentsPosts> findAllByPostId(Long postId) {
        return commentsPostsRepository.findAllByPostId(postId);
    }

    public void deleteById(Long commentId) {
        User user = securityService.getUser();
        try {
            checkIfExistsByIdAndEmail(commentId, user.getEmail());
            commentsPostsRepository.deleteById(commentId);
        } catch (NotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    void checkIfExistsByIdAndEmail(Long commentId, String email){
        commentsPostsRepository.findByIdAndUserEmail(commentId, email)
                .orElseThrow(() -> new NotFoundException(String.format(COMMENT_BY_OWNER_NOT_FOUND, commentId, email)));
    }

}
