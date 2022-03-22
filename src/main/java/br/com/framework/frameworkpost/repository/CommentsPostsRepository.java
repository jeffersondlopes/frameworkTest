package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.CommentsPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsPostsRepository extends JpaRepository<CommentsPosts, Long> {
}
