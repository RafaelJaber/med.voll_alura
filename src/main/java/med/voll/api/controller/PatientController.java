package med.voll.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import med.voll.api.dto.PatientCreateDto;
import med.voll.api.dto.PatientReadMinDto;
import med.voll.api.dto.PatientUpdateDto;
import med.voll.api.model.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<PatientReadMinDto> GetAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(PatientReadMinDto::new);
    }


    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid PatientCreateDto dto) {
        repository.save(new Patient(dto));
    }

    @PutMapping
    @Transactional
    public void Update(@RequestBody @Valid PatientUpdateDto dto) {
        try {
            Patient patient = repository.getReferenceById(dto.id());
            patient.Update(dto);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
