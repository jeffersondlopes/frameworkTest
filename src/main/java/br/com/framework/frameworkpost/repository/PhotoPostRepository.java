package br.com.framework.frameworkpost.repository;

import br.com.framework.frameworkpost.domain.PhotosPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoPostRepository extends JpaRepository<PhotosPost, Long> {



}
