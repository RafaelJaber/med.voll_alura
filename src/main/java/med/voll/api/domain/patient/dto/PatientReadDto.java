package med.voll.api.domain.patient.dto;

import med.voll.api.domain.address.dto.AddressDto;
import med.voll.api.domain.patient.model.Patient;

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
