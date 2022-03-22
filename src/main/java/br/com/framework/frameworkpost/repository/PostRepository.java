package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
