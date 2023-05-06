package med.voll.api.domain.schedule.validators;

import med.voll.api.domain.schedule.dto.ScheduleDataDTO;

public interface ScheduleValidator {
    void validate(ScheduleDataDTO dto);
}
