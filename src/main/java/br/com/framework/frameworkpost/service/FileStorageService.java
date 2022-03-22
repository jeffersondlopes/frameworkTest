package br.com.framework.frameworkpost.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface FileStorageService {

	void savePhoto(FileToStorage fileToStorage);
	
	@Builder
	@Getter
	class FileToStorage {
		private String nomeAquivo;
		private InputStream inputStream;
	}
	
}
