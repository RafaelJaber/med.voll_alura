package med.voll.api.domain.schedule.validators;

import med.voll.api.domain.patient.repository.PatientRepository;
import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.infrastructure.exception.SchedulingValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientActiveValidator implements ScheduleValidator {

    @Autowired private PatientRepository repository;

    public void validate(ScheduleDataDTO dto) {

        boolean statusPatient = repository.findByStatusTrueAndById(dto.idPatient());
        if(!statusPatient)
            throw new SchedulingValidationException("Consultation cannot be scheduled with excluded patient!");
    }
}
