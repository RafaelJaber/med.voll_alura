package med.voll.api.domain.schedule.dto;

import med.voll.api.domain.schedule.model.Schedule;

import java.time.LocalDateTime;

public record ScheduleDetailingDataDTO(
        Long id,
        Long idDoctor,
        Long idPatient,
        LocalDateTime date
) {
    public ScheduleDetailingDataDTO(Schedule schedule) {
        this(
                schedule.getId(),
                schedule.getDoctor().getId(),
                schedule.getPatient().getId(),
                schedule.getDate()
        );
    }
}
