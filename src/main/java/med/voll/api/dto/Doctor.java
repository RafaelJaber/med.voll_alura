package med.voll.api.dto;

public record Doctor(String name, String email, String crm, Specialty specialty, Address address) {
}
