package oracle.alura.challenge.forohub.application.exception;

public class DuplicadoException extends RuntimeException {

    public DuplicadoException(String mensaje) {
        super(mensaje);
    }
}