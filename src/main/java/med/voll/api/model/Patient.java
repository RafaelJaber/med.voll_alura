package med.voll.api.model;

public record Patient(String name, String email, String telephone, String document, Address address) {
}
