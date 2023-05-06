package med.voll.api.controller;


import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.domain.schedule.dto.ScheduleDetailingDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody ScheduleDataDTO dto) {
        System.out.println(dto);
        return ResponseEntity.ok(new ScheduleDetailingDataDTO(null, null, null, null));
    }
}
