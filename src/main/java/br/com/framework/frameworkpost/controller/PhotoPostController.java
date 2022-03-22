package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.PhotosPost;
import br.com.framework.frameworkpost.model.input.PhotoPostInput;
import br.com.framework.frameworkpost.service.PhotoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/foto")
public class PhotoPostController {

    @Autowired
    private PhotoPostService photoPostService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PhotosPost savePhoto(@PathVariable Long postId, PhotoPostInput photoPostInput) {
        return photoPostService.save(postId, photoPostInput);
    }

    @DeleteMapping("/{fileName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoto(@PathVariable Long postId, @PathVariable String fileName){
        photoPostService.delete(postId, fileName);
    }


}
