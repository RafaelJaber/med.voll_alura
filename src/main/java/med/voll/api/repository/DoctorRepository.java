package med.voll.api.repository;

import med.voll.api.model.Doctor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByStatusTrue(Pageable pageable);
}
