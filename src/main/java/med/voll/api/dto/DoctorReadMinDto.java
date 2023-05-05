package med.voll.api.dto;

import med.voll.api.model.Doctor;

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
