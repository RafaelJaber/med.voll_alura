package med.voll.api.infrastructure.exception;

public class SchedulingValidationException extends RuntimeException {
    public SchedulingValidationException(String message) {
        super(message);
    }
}
