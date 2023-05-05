package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDto(
        @NotNull
        Long id,
        String name,
        String telephone,
        @Valid
        AddressDto address
) {
}
