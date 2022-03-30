package br.com.framework.frameworkpost.config.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageProblem {

	private Integer status;
	private String title;
	private LocalDateTime dateTime;
	private String message;
	private String detail;
	
}
