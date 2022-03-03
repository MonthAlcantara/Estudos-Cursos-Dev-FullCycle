package io.github.monthalcantara.CatalogoVideo.domain.exception;

public class DomainException extends RuntimeException{

    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }
}
