package med.voll.api.domain.schedule.validators;

import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.infrastructure.exception.SchedulingValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ScheduleTimeValidator implements ScheduleValidator {
    public void validate(ScheduleDataDTO dto) {
        LocalDateTime dateSchedule = dto.date();
        LocalDateTime now = LocalDateTime.now();

        long differenceInMinutes = Duration.between(now, dateSchedule).toMinutes();

        if (differenceInMinutes < 30) {
            throw new SchedulingValidationException("Consultation must be scheduled at least 30 minutes in advance!");
        }
    }
}
