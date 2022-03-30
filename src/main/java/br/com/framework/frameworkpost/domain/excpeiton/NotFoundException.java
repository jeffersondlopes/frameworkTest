package br.com.framework.frameworkpost.domain.excpeiton;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotFoundException(String mensagem) {
        super(mensagem);
    }

    public NotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
