package med.voll.api.domain.schedule.validators;

import med.voll.api.domain.doctor.repository.DoctorRepository;
import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.infrastructure.exception.SchedulingValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorActiveValidator implements ScheduleValidator {

    @Autowired private DoctorRepository repository;

    public void validate(ScheduleDataDTO dto) {
        if (dto.idDoctor() == null) {
            return;
        }

        boolean statusDoctor = repository.findByStatusTrueAndById(dto.idDoctor());
        if(!statusDoctor)
            throw new SchedulingValidationException("Consultation cannot be scheduled with excluded doctor!");
    }
}
