package med.voll.api.domain.schedule.validators;

import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.domain.schedule.repository.ScheduleRepository;
import med.voll.api.infrastructure.exception.SchedulingValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientWithAnotherScheduleOnDayValidator implements ScheduleValidator {

    @Autowired private ScheduleRepository repository;

    public void validate(ScheduleDataDTO dto) {
        LocalDateTime firstHour = dto.date().withHour(7);
        LocalDateTime lastHour = dto.date().withHour(18);
        boolean patientAnotherScheduleOnDay = repository
                .existsByPatientIdAndDateBetween(dto.idPatient(), firstHour, lastHour);
        if (patientAnotherScheduleOnDay)
            throw new SchedulingValidationException("Patient already has an appointment scheduled that day!");
    }
}
