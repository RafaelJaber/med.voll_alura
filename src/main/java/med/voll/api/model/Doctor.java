package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DoctorCreateDto;
import med.voll.api.dto.DoctorUpdateDto;
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
    private String telephone;
    private String crm;
    private Boolean status;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorCreateDto dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.telephone = dto.telephone();
        this.crm = dto.crm();
        this.specialty = dto.specialty();
        this.address = new Address(dto.address());
        this.status = true;
    }

    public void Update(DoctorUpdateDto dto) {
        if (dto.name() != null) this.name = dto.name();
        if (dto.telephone() != null) this.telephone = dto.telephone();
        if (dto.email() != null) this.email = dto.email();
        if (dto.address() != null) this.address.Update(dto.address());
    }

    public void Delete() {
        this.status = false;
    }
}
