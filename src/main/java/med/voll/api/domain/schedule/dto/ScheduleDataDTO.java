package med.voll.api.domain.schedule.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.shared.Specialty;

import java.time.LocalDateTime;

public record ScheduleDataDTO(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty
) {
}
