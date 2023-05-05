package med.voll.api.domain.shared.dto;

import java.sql.Timestamp;
import java.util.List;

public record DataErrorValidationDTO(
        Timestamp timestamp,
        String statusCode,
        List<FieldErrorValidationDTO> fieldErrors,
        int errorCode
) {
}
