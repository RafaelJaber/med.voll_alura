package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorDto;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid DoctorDto dto) {
        repository.save(new Doctor(dto));
    }

}
