package med.voll.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.dto.DoctorCreateDto;
import med.voll.api.domain.doctor.dto.DoctorReadDto;
import med.voll.api.domain.doctor.dto.DoctorReadMinDto;
import med.voll.api.domain.doctor.dto.DoctorUpdateDto;
import med.voll.api.domain.doctor.model.Doctor;
import med.voll.api.domain.doctor.repository.DoctorRepository;
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
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<DoctorReadMinDto>> GetAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<DoctorReadMinDto> page = repository
                .findAllByStatusTrue(pageable)
                .map(DoctorReadMinDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorReadDto> GetById(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        DoctorReadDto doctorDto = new DoctorReadDto(doctor);
        return ResponseEntity.ok(doctorDto);
    }

    @PostMapping
    @Transactional
    public ResponseEntity Create(@RequestBody @Valid DoctorCreateDto dto, UriComponentsBuilder uriBuilder) {
        Doctor doctor = repository.save(new Doctor(dto));
        URI locator = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(locator).body(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity Update(@RequestBody @Valid DoctorUpdateDto dto) {

        Doctor doctor = repository.getReferenceById(dto.id());
        doctor.Update(dto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity Delete(@PathVariable Long id) {
        // repository.deleteById(id); // Essa é uma exclusão física

        Doctor doctor = repository.getReferenceById(id);
        doctor.Delete();
        return ResponseEntity.noContent().build();

    }

}
