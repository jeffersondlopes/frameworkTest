package br.com.framework.frameworkpost.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

import static java.util.UUID.*;

public interface FileStorageService {

	void save(FileToStorage fileToStorage);
	void remove(String nomeArquivo);
	InputStream getFile(String nomeArquivo);

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
