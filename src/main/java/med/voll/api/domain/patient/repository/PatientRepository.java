package med.voll.api.domain.patient.repository;

import med.voll.api.domain.patient.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByStatusTrue(Pageable pageable);

    @Query("SELECT p.status FROM Patient p WHERE p.id = :id")
    boolean findByStatusTrueAndById(Long id);
}
