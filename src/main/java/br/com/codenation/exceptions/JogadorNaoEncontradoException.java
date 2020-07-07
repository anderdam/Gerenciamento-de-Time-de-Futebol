package br.com.codenation.exceptions;

public class JogadorNaoEncontradoException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JogadorNaoEncontradoException(String msg) {
        super(msg);
    }
}