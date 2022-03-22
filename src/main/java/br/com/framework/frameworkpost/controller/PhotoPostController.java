package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.domain.PhotosPost;
import br.com.framework.frameworkpost.model.input.PhotoPostInput;
import br.com.framework.frameworkpost.service.PhotoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts/{postId}/foto")
public class PhotoPostController {

    @Autowired
    private PhotoPostService photoPostService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotosPost atualizarFoto(@PathVariable Long postId, PhotoPostInput photoPostInput) {
        return photoPostService.save(postId, photoPostInput);
    }


//    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void atualizarFoto(@PathVariable Long postId, PhotoPostInput photoPostInput) {
//
//        var nomeArquivo = UUID.randomUUID().toString()
//                + "_" + photoPostInput.getFile().getOriginalFilename();
//
//        var arquivoFoto = Path.of("/home/jefferson/jefferson/entrevistas/FrameWork/download-foto-test", nomeArquivo);
//
//        try {
//            photoPostInput.getFile().transferTo(arquivoFoto);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//

//    }





}
