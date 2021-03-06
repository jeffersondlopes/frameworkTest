package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.CommentsPosts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentsPostsRepository extends JpaRepository<CommentsPosts, Long> {

    List<CommentsPosts> findAllByPostId(Long postId);

    Optional<CommentsPosts> findByIdAndUserEmail(Long id, String email);

}
