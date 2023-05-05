package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DoctorCreateDto;
import med.voll.api.dto.DoctorReadMinDto;
import med.voll.api.model.Doctor;
import med.voll.api.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
                .findAll(pageable)
                .map(DoctorReadMinDto::new);
    }

    @PostMapping
    @Transactional
    public void Create(@RequestBody @Valid DoctorCreateDto dto) {
        repository.save(new Doctor(dto));
    }

}
