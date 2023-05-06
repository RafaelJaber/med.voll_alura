package med.voll.api.domain.schedule.repository;

import med.voll.api.domain.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
