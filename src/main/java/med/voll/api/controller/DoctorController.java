package med.voll.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import med.voll.api.dto.DoctorCreateDto;
import med.voll.api.dto.DoctorReadDto;
import med.voll.api.dto.DoctorReadMinDto;
import med.voll.api.dto.DoctorUpdateDto;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
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
        try {
            Doctor doctor = repository.getReferenceById(id);
            DoctorReadDto doctorDto = new DoctorReadDto(doctor);
            return ResponseEntity.ok(doctorDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
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
        try {
            Doctor doctor = repository.getReferenceById(dto.id());
            doctor.Update(dto);
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
        // repository.deleteById(id); // Essa é uma exclusão física
        try {
            Doctor doctor = repository.getReferenceById(id);
            doctor.Delete();
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        }
    }

}
