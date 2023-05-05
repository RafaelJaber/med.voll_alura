package med.voll.api.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.PatientDto;


@Table( name = "TB_Patient" )
@Entity( name = "PatientDto" )
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String document;

    @Embedded
    private Address address;

    public Patient(PatientDto dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.telephone = dto.telephone();
        this.document = dto.document();
        this.address = new Address(dto.address());
    }
}
