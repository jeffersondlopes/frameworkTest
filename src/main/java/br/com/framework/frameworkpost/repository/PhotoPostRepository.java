package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.PhotosPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoPostRepository extends JpaRepository<PhotosPost, Long> {

    Optional<PhotosPost> findByPostIdAndNameFile(Long id, String fileName);

}
