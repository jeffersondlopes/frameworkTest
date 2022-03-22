package br.com.framework.frameworkpost.model.input;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PhotoPostInput {

    private MultipartFile file;
    private String description;

}
