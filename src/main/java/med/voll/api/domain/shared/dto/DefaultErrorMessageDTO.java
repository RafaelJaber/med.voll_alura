package med.voll.api.domain.shared.dto;

import java.sql.Timestamp;

public record DefaultErrorMessageDTO(
        Timestamp timestamp,
        String statusCode,
        String message,
        int errorCode
) {
}
