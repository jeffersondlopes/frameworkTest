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
import java.io.InputStream;

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
        FileToStorage fileToStorage = null;
        try {
            fileToStorage = buildFileStorage(photoPostInput.getFile().getInputStream(), photosPost.getNameFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        photStorageService.savePhoto(fileToStorage);
        return photoPostRepository.save(photosPost);

    }

    private PhotosPost buildPhotoPost(PhotoPostInput photoPostInput) {
        return PhotosPost.builder()
                .description(photoPostInput.getDescription())
                .nameFile(photStorageService.generateNameFile(photoPostInput.getFile().getOriginalFilename()))
                .contentType(photoPostInput.getFile().getContentType())
                .size(photoPostInput.getFile().getSize())
                .build();
    }

    private FileToStorage buildFileStorage(InputStream inputStream, String fileName) {
        return FileStorageService.FileToStorage.builder()
                .nomeAquivo(fileName)
                .inputStream(inputStream)
                .build();
    }

    @Transactional
    public void delete(Long postId, String fileName) {
        PhotosPost photosPost = photoPostRepository.findByPostIdAndNameFile(postId, fileName)
                .orElseThrow(() -> new RuntimeException("Arquivo Não encontrado"));
         photoPostRepository.delete(photosPost);
        photStorageService.remover(fileName);
    }

}
