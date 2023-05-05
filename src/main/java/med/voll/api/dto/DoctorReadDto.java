package med.voll.api.dto;

import med.voll.api.model.Address;
import med.voll.api.model.Doctor;

public record DoctorReadDto(
        Long id,
        String name,
        String email,
        String telephone,
        String crm,
        Specialty specialty,
        Boolean status,
        AddressDto address
) {
    public DoctorReadDto(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getTelephone(),
                doctor.getCrm(),
                doctor.getSpecialty(),
                doctor.getStatus(),
                new AddressDto(doctor.getAddress())
        );
    }
}
