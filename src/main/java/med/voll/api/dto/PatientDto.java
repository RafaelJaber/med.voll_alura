package med.voll.api.dto;

public record PatientDto(String name, String email, String telephone, String document, AddressDto addressDto) {
}
