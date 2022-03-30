package br.com.framework.frameworkpost.config.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageProblem {

	private Integer status;
	private String title;
	private LocalDateTime dateTime;
	private String message;
	private String detail;
	
}
