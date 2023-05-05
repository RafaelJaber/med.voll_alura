package med.voll.api.dto;

import med.voll.api.model.Patient;

public record PatientReadMinDto(
        Long id,
        String name,
        String email,
        String document
) {
    public PatientReadMinDto(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getDocument()
        );
    }
}
