package med.voll.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import med.voll.api.dto.DoctorCreateDto;
import med.voll.api.dto.DoctorReadMinDto;
import med.voll.api.dto.DoctorUpdateDto;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<DoctorReadMinDto> GetAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository
                .findAllByStatusTrue(pageable)
                .map(DoctorReadMinDto::new);
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid DoctorCreateDto dto) {
        repository.save(new Doctor(dto));
    }

    @PutMapping
    @Transactional
    public void Update(@RequestBody @Valid DoctorUpdateDto dto) {
        try {
            Doctor doctor = repository.getReferenceById(dto.id());
            doctor.Update(dto);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void Delete(@PathVariable Long id) {
        // repository.deleteById(id); // Essa é uma exclusão física
        try {
            Doctor doctor = repository.getReferenceById(id);
            doctor.Delete();

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
