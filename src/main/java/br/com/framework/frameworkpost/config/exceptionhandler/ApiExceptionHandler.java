package br.com.framework.frameworkpost.config.exceptionhandler;

import br.com.framework.frameworkpost.domain.excpeiton.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
			= "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
			+ "o problema persistir, entre em contato com o administrador do sistema.";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return handleExceptionInternal(ex, MSG_ERRO_GENERICA_USUARIO_FINAL, new HttpHeaders(),status, request);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(NotFoundException e) {
		MessageProblem problema = MessageProblem.builder()
				.status(HttpStatus.NOT_FOUND.value())
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
															 HttpStatus status, WebRequest request) {

		if (body == null) {
			body = MessageProblem.builder()
					.status(status.value())
					.title(status.getReasonPhrase())
					.dateTime(LocalDateTime.now())
					.message(status.getReasonPhrase())
					.detail(ex.getMessage())
					.build();
		} else if (body instanceof String) {
			body = MessageProblem.builder()
					.status(status.value())
					.title(status.getReasonPhrase())
					.dateTime(LocalDateTime.now())
					.message((String) body)
					.detail(ex.getMessage())
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
}
