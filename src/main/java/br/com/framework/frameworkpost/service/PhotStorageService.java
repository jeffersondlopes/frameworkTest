package br.com.framework.frameworkpost.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PhotStorageService implements FileStorageService {

    @Value("${framework.storage.local.diretorio-fotos}")
    private Path directorPhotos;

    @Override
    public void savePhoto(NewPhoto newPhoto) {
        try {
            Path arquivoPath = getArquivoPath(newPhoto.getNomeAquivo());
            FileCopyUtils.copy(newPhoto.getInputStream(),
                    Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível armazenar arquivo.", e);
        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return directorPhotos.resolve(Path.of(nomeArquivo));
    }

}
