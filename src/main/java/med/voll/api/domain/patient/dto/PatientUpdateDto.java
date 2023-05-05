package med.voll.api.domain.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.dto.AddressDto;

public record PatientUpdateDto(
        @NotNull
        Long id,
        String name,
        String telephone,
        @Valid
        AddressDto address
) {
}
