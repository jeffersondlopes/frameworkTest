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
    public void savePhoto(FileToStorage fileToStorage) {
        try {
            Path arquivoPath = getFilePath(fileToStorage.getNomeAquivo());
            FileCopyUtils.copy(fileToStorage.getInputStream(),
                    Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível armazenar arquivo.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path arquivoPath = getFilePath(nomeArquivo);
            Files.deleteIfExists(arquivoPath);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir arquivo.", e);
        }
    }

    private Path getFilePath(String fileName) {
        return directorPhotos.resolve(Path.of(fileName));
    }

}
