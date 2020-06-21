package app.exception;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException(String s) {
        super(s);
    }
}
