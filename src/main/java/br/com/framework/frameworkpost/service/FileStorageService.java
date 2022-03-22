package br.com.framework.frameworkpost.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

import static java.util.UUID.*;

public interface FileStorageService {

	void savePhoto(FileToStorage fileToStorage);
	void remover(String nomeArquivo);

	default String generateNameFile(String nomeOriginal) {
		return randomUUID() + "_" + nomeOriginal;
	}

	@Builder
	@Getter
	class FileToStorage {
		private String nomeAquivo;
		private InputStream inputStream;
	}
	
}
