package med.voll.api.domain.schedule.dto;

import java.time.LocalDateTime;

public record ScheduleDetailingDataDTO(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date
) {
}
