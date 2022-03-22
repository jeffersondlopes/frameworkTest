package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUsersId(Long userId);
}
