package br.com.framework.frameworkpost.service;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

public interface FileStorageService {

	void savePhoto(NewPhoto novaFoto);
	
	@Builder
	@Getter
	class NewPhoto {
		private String nomeAquivo;
		private InputStream inputStream;
	}
	
}
