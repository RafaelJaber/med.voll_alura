package med.voll.api.model;

public record Doctor(String name, String email, String crm, Specialty specialty, Address address) {
}
