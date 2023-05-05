package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.doctor.model.Doctor;
import med.voll.api.domain.shared.Specialty;

public record DoctorReadMinDto(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorReadMinDto(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpecialty()
        );
    }
}
