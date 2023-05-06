package med.voll.api.domain.doctor.repository;

import med.voll.api.domain.doctor.model.Doctor;
import med.voll.api.domain.shared.Specialty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByStatusTrue(Pageable pageable);

    @Query("""
            SELECT d FROM Doctor d
            WHERE
                d.status = true
                AND d.specialty = :specialty
                AND d.id NOT IN (
                    SELECT s.doctor.id FROM Schedule s
                    WHERE s.date = :date
                )
            ORDER BY RAND() LIMIT 1
            """)
    Doctor chooseFreeRandomDoctorOnDate(Specialty specialty, LocalDateTime date);

    @Query("SELECT d.status FROM Doctor d WHERE d.id = :id")
    boolean findByStatusTrueAndById(Long id);
}
