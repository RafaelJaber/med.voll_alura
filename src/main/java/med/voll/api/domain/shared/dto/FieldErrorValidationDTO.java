package med.voll.api.domain.shared.dto;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public record FieldErrorValidationDTO(String field, String message) {
    public FieldErrorValidationDTO(FieldError error) {
        this(
                error.getField(),
                error.getDefaultMessage()
        );
    }
}
