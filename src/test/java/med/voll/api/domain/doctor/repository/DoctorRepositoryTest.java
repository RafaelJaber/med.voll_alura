package med.voll.api.domain.doctor.repository;

import med.voll.api.domain.address.dto.AddressDto;
import med.voll.api.domain.doctor.dto.DoctorCreateDto;
import med.voll.api.domain.doctor.model.Doctor;
import med.voll.api.domain.patient.dto.PatientCreateDto;
import med.voll.api.domain.patient.model.Patient;
import med.voll.api.domain.schedule.model.Schedule;
import med.voll.api.domain.shared.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
//@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired private DoctorRepository doctorRepository;
    @Autowired private TestEntityManager em;

    @Test
    @DisplayName("It should return null when the only registered doctor is not available on the date")
    void chooseFreeRandomDoctorOnDateScenario1() {
        LocalDateTime nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        Doctor doctor = createDoctor("Doctor", "doc@voll.med", "123456", Specialty.CARDIOLOGIA);
        Patient patient = createPatient("Patient", "patient@voll.med", "00000000000");
        Schedule schedule = registerSchedule(doctor, patient, nextMondayAt10);

        Doctor doctorFree = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Return doctor when available in data")
    void chooseFreeRandomDoctorOnDateScenario2() {
        LocalDateTime nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        Doctor doctor = createDoctor("Doctor", "doc@voll.med", "123456", Specialty.CARDIOLOGIA);

        Doctor doctorFree = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.CARDIOLOGIA, nextMondayAt10);
        assertThat(doctorFree).isEqualTo(doctor);
    }

    @Test
    void findByStatusTrueAndById() {
    }

    private Schedule registerSchedule(Doctor doctor, Patient patient, LocalDateTime date) {
        Schedule schedule = new Schedule(null, doctor, patient, date, null);
        em.persist(schedule);
        return schedule;
    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty) {
        Doctor doctor = new Doctor(dataDoctor(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String cpf) {
        Patient patient = new Patient(dataPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorCreateDto dataDoctor(String name, String email, String crm, Specialty specialty) {
        return new DoctorCreateDto(
                name,
                email,
                "37999999999",
                crm,
                specialty,
                dataAddress()
        );
    }

    private PatientCreateDto dataPatient(String name, String email, String document) {
        return new PatientCreateDto(
                name,
                email,
                "61999999999",
                document,
                dataAddress()
        );
    }

    private AddressDto dataAddress() {
        return new AddressDto(
                "Rua xpt",
                "bairro",
                "00000000",
                "Bom Despacho",
                "MG",
                null,
                null
        );
    }
}