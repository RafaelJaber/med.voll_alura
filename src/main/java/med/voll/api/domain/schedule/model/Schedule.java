package med.voll.api.domain.schedule.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.doctor.model.Doctor;
import med.voll.api.domain.patient.model.Patient;
import med.voll.api.domain.shared.CancellationReasonEnum;

import java.time.LocalDateTime;

@Table( name = "TB_Schedule")
@Entity( name = "Schedule")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "cancellation_reason")
    private CancellationReasonEnum reason;


    public void cancel(CancellationReasonEnum reason) {
        this.reason = reason;
    }
}
