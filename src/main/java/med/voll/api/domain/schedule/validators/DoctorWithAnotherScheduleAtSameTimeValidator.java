package med.voll.api.domain.schedule.validators;

import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.domain.schedule.repository.ScheduleRepository;
import med.voll.api.infrastructure.exception.SchedulingValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAnotherScheduleAtSameTimeValidator implements ScheduleValidator {

    @Autowired private ScheduleRepository repository;

    public void validate(ScheduleDataDTO dto) {
        boolean doctorHasAnotherAppointmentAtSameTime = repository.existsByDoctorIdAndDate(dto.idDoctor(), dto.date());

        if (doctorHasAnotherAppointmentAtSameTime)
            throw new SchedulingValidationException("Doctor already has another appointment scheduled at the same time!");
    }
}
