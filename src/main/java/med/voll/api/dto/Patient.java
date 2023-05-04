package med.voll.api.dto;

public record Patient(String name, String email, String telephone, String document, Address address) {
}
