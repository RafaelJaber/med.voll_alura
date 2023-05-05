package med.voll.api.dto;

import med.voll.api.model.Patient;

public record PatientReadDto(
        Long id,
        String name,
        String email,
        String telephone,
        String document,
        Boolean status,
        AddressDto address

) {
    public PatientReadDto(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getTelephone(),
                patient.getDocument(),
                patient.getStatus(),
                new AddressDto(patient.getAddress())
        );
    }
}
