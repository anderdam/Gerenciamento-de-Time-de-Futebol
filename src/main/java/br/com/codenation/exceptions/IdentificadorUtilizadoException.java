package br.com.codenation.exceptions;

public class IdentificadorUtilizadoException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IdentificadorUtilizadoException(String msg) {
        super(msg);
    }
}