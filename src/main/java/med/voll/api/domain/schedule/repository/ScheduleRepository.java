package med.voll.api.domain.schedule.repository;

import med.voll.api.domain.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    boolean existsByDoctorIdAndDate(Long aLong, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long aLong, LocalDateTime firstHour, LocalDateTime lastHour);
}
