package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.PhotosPost;
import br.com.framework.frameworkpost.domain.Post;
import br.com.framework.frameworkpost.model.input.PhotoPostInput;
import br.com.framework.frameworkpost.repository.PhotoPostRepository;
import br.com.framework.frameworkpost.service.FileStorageService.FileToStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class PhotoPostService {

    private final PhotoPostRepository photoPostRepository;
    private final PostsService postsService;
    private final PhotStorageService photStorageService;

    @Autowired
    public PhotoPostService(PhotoPostRepository photoPostRepository, PostsService postsService, PhotStorageService photStorageService) {
        this.photoPostRepository = photoPostRepository;
        this.postsService = postsService;
        this.photStorageService = photStorageService;
    }

    @Transactional
    public PhotosPost save(Long postId, PhotoPostInput photoPostInput) {
        Post post = postsService.findById(postId);
        PhotosPost photosPost = buildPhotoPost(photoPostInput);
        photosPost.setPost(post);
        FileToStorage fileToStorage = buildFileStorage(photoPostInput);
        photStorageService.savePhoto(fileToStorage);
        return photoPostRepository.save(photosPost);

    }

    private PhotosPost buildPhotoPost(PhotoPostInput photoPostInput){
        return PhotosPost.builder()
                .description(photoPostInput.getDescription())
                .nameFile(photStorageService.generateNameFile(photoPostInput.getFile().getOriginalFilename()))
                .contentType(photoPostInput.getFile().getContentType())
                .size(photoPostInput.getFile().getSize())
                .build();
    }

    private FileToStorage buildFileStorage(PhotoPostInput photoPostInput) {
        try {
            return FileStorageService.FileToStorage.builder()
                    .nomeAquivo(photStorageService.generateNameFile(photoPostInput.getFile().getOriginalFilename()))
                    .inputStream(photoPostInput.getFile().getInputStream())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gerar o FileStorage.",e);
        }
    }

}
