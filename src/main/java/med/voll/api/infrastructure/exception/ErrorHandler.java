package med.voll.api.infrastructure.exception;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.shared.dto.DataErrorValidationDTO;
import med.voll.api.domain.shared.dto.DefaultErrorMessageDTO;
import med.voll.api.domain.shared.dto.FieldErrorValidationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity errorHandler400Sql(SQLIntegrityConstraintViolationException ex) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DefaultErrorMessageDTO errorBody = new DefaultErrorMessageDTO(
                now,
                "400",
                ex.getMessage(),
                ex.getErrorCode()
        );
        return ResponseEntity.badRequest().body(errorBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400Fields(MethodArgumentNotValidException ex){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<FieldErrorValidationDTO> errors = ex.getFieldErrors().stream().map(FieldErrorValidationDTO::new).toList();
        DataErrorValidationDTO errorBody = new DataErrorValidationDTO(
                now,
                "400",
                errors,
                0
        );
        return ResponseEntity.badRequest().body(errorBody);
    }

    @ExceptionHandler(SchedulingValidationException.class)
    public ResponseEntity errorSchedulingValidationException(SchedulingValidationException ex) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DefaultErrorMessageDTO errorBody = new DefaultErrorMessageDTO(
                now,
                "400",
                ex.getMessage(),
                10
        );
        return ResponseEntity.badRequest().body(errorBody);
    }

}
