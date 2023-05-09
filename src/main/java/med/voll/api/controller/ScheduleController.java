package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.schedule.dto.ScheduleCancellationDataDTO;
import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.domain.schedule.dto.ScheduleDetailingDataDTO;
import med.voll.api.domain.schedule.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
@SecurityRequirement(name = "bearer-key")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid ScheduleDataDTO dto) {
        ScheduleDetailingDataDTO scheduleDetailingDataDTO = scheduleService.toSchedule(dto);
        return ResponseEntity.ok(scheduleDetailingDataDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid ScheduleCancellationDataDTO dto) {
        scheduleService.cancelAppointment(dto);
        return ResponseEntity.noContent().build();
    }
}
