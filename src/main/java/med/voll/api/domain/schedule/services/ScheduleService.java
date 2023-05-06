package med.voll.api.domain.schedule.services;

import med.voll.api.domain.doctor.model.Doctor;
import med.voll.api.domain.doctor.repository.DoctorRepository;
import med.voll.api.domain.patient.model.Patient;
import med.voll.api.domain.patient.repository.PatientRepository;
import med.voll.api.domain.schedule.dto.ScheduleCancellationDataDTO;
import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.domain.schedule.model.Schedule;
import med.voll.api.domain.schedule.repository.ScheduleRepository;
import med.voll.api.infrastructure.exception.SchedulingValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired private ScheduleRepository scheduleRepository;
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private PatientRepository patientRepository;

    public void toSchedule(ScheduleDataDTO dto){
        if (!patientRepository.existsById(dto.idPatient()))
            throw new SchedulingValidationException("Informed patient id does not exist!");
        if ( dto.idDoctor() != null && !doctorRepository.existsById(dto.idPatient()))
            throw new SchedulingValidationException("Informed doctor id does not exist!");

        Doctor doctor = chooseDoctor(dto);
        Patient patient = patientRepository.getReferenceById(dto.idPatient());
        Schedule schedule = new Schedule(null, doctor, patient, dto.date(), null);
        scheduleRepository.save(schedule);
    }

    private Doctor chooseDoctor(ScheduleDataDTO dto) {
        if (dto.idDoctor() != null) {
            return doctorRepository.getReferenceById(dto.idDoctor());
        }
        if (dto.specialty() == null)
            throw new SchedulingValidationException("Specialty is mandatory when the doctor is not chosen!");

        return doctorRepository.chooseFreeRandomDoctorOnDate(dto.specialty(), dto.date());
    }

    public void cancelAppointment(ScheduleCancellationDataDTO dto) {
        if (!scheduleRepository.existsById(dto.idSchedule()))
            throw new SchedulingValidationException("Appointment not found in database!");

        Schedule schedule = scheduleRepository.getReferenceById(dto.idSchedule());
        schedule.cancel(dto.reason());
    }
}
