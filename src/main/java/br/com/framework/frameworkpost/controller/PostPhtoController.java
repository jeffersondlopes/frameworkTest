package br.com.framework.frameworkpost.controller;

import br.com.framework.frameworkpost.model.input.PhotoPostInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/posts/{postId/foto}")
public class PostPhtoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long postId, PhotoPostInput photoPostInput) {

        var nomeArquivo = UUID.randomUUID().toString()
                + "_" + photoPostInput.getFile().getOriginalFilename();

        var arquivoFoto = Path.of("/home/jefferson/jefferson/entrevistas/FrameWork/download-foto-test", nomeArquivo);

        System.out.println(photoPostInput.getDescription());
        System.out.println(arquivoFoto);
        System.out.println(photoPostInput.getFile().getContentType());

        try {
            photoPostInput.getFile().transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
