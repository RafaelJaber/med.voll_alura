package med.voll.api.dto;

public record DoctorDto(String name, String email, String crm, Specialty specialty, AddressDto addressDto) {
}
