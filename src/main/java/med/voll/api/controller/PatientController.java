package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.dto.PatientCreateDto;
import med.voll.api.domain.patient.dto.PatientReadDto;
import med.voll.api.domain.patient.dto.PatientReadMinDto;
import med.voll.api.domain.patient.dto.PatientUpdateDto;
import med.voll.api.domain.patient.model.Patient;
import med.voll.api.domain.patient.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<PatientReadMinDto>> GetAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<PatientReadMinDto> page = repository
                .findAllByStatusTrue(pageable)
                .map(PatientReadMinDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientReadDto> GetById(@PathVariable Long id){
        try {
            Patient patient = repository.getReferenceById(id);
            PatientReadDto patientDto = new PatientReadDto(patient);
            return ResponseEntity.ok(patientDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity Create(@RequestBody @Valid PatientCreateDto dto, UriComponentsBuilder uriBuilder) {
        Patient patient = repository.save(new Patient(dto));
        URI locator = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(locator).body(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity Update(@RequestBody @Valid PatientUpdateDto dto) {
        try {
            Patient patient = repository.getReferenceById(dto.id());
            patient.Update(dto);
            return ResponseEntity.accepted().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity Delete(@PathVariable Long id) {
        try {
            Patient patient = repository.getReferenceById(id);
            patient.Delete();
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

}
