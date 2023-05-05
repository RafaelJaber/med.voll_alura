package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.PatientDto;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid PatientDto dto) {
        repository.save(new Patient(dto));
    }

}
