package med.voll.api.domain.address.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.dto.AddressDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String publicPlace;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;
    private String complement;
    private String number;

    public Address(AddressDto dto) {
        this.publicPlace = dto.publicPlace();
        this.neighborhood = dto.neighborhood();
        this.zipCode = dto.zipCode();
        this.city = dto.city();
        this.state = dto.state();
        this.complement = dto.complement();
        this.number = dto.number();
    }

    public void Update(AddressDto dto) {
        if (dto.publicPlace() != null) this.publicPlace = dto.publicPlace();
        if (dto.neighborhood() != null) this.neighborhood = dto.neighborhood();
        if (dto.zipCode() != null) this.zipCode = dto.zipCode();
        if (dto.city() != null) this.city = dto.city();
        if (dto.state() != null) this.state = dto.state();
        if (dto.complement() != null) this.complement = dto.complement();
        if (dto.number() != null) this.number = dto.number();
    }
}
