package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorDto;
import med.voll.api.dto.Specialty;

@Table( name = "TB_Doctor")
@Entity( name = "DoctorDto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorDto dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.crm = dto.crm();
        this.specialty = dto.specialty();
        this.address = new Address(dto.address());
    }
}
