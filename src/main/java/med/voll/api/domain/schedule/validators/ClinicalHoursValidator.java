package med.voll.api.domain.schedule.validators;

import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.infrastructure.exception.SchedulingValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ClinicalHoursValidator implements ScheduleValidator {

    public void validate(ScheduleDataDTO dto) {
        LocalDateTime dateSchedule = dto.date();

        boolean isSunday = dateSchedule.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = dateSchedule.getHour() < 7;
        boolean afterOpening = dateSchedule.getHour() > 18;

        if (isSunday || beforeOpening || afterOpening) {
            throw new SchedulingValidationException("Out-of-hours consultation!");
        }
    }
}
