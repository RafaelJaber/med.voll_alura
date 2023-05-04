package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressDto;

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
}
