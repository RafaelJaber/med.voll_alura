package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

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
