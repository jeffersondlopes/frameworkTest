package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {

    List<Posts> findAllByUsersId(Long userId);
}
