package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.address.dto.AddressDto;
import med.voll.api.domain.doctor.model.Doctor;
import med.voll.api.domain.shared.Specialty;

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
