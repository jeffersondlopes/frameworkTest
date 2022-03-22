package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.PhotosPost;
import br.com.framework.frameworkpost.domain.Post;
import br.com.framework.frameworkpost.model.input.PhotoPostInput;
import br.com.framework.frameworkpost.repository.PhotoPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PhotoPostService {

    private final PhotoPostRepository photoPostRepository;
    private final PostsService postsService;

    @Autowired
    public PhotoPostService(PhotoPostRepository photoPostRepository, PostsService postsService) {
        this.photoPostRepository = photoPostRepository;
        this.postsService = postsService;
    }

    @Transactional
    public PhotosPost save(Long postId, PhotoPostInput photoPostInput) {
        Post post = postsService.findById(postId);
        PhotosPost photosPost = buildPhotoPost(photoPostInput);
        photosPost.setPost(post);
        return photoPostRepository.save(photosPost);

    }

    private PhotosPost buildPhotoPost(PhotoPostInput photoPostInput){
        return PhotosPost.builder()
                .description(photoPostInput.getDescription())
                .nameFile(photoPostInput.getFile().getOriginalFilename())
                .contentType(photoPostInput.getFile().getContentType())
                .size(photoPostInput.getFile().getSize())
                .build();
    }

}
