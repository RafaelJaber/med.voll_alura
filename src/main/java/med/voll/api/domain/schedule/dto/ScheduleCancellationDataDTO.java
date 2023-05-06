package med.voll.api.domain.schedule.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.shared.CancellationReasonEnum;

public record ScheduleCancellationDataDTO(
        @NotNull
        Long idSchedule,

        @NotNull
        CancellationReasonEnum reason
) {
}
