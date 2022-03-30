package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUsersId(Long userId);

    @Query("select p From Post p " +
           " inner join fetch p.users u" +
           " where p.id = :postId" +
            "  and u.email = :email")
    Optional<Post> findyPostByOwnerEmail(Long postId,String email);

}
