package br.com.codenation.exceptions;

public class TimeNaoEncontradoException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TimeNaoEncontradoException(String msg) {
        super(msg);
    }
}