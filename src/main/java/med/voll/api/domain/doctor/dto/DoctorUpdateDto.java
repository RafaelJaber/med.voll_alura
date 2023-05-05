package med.voll.api.domain.doctor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.dto.AddressDto;

public record DoctorUpdateDto(
        @NotNull
        Long id,
        String name,
        String telephone,
        String email,
        @Valid
        AddressDto address
) {
}
