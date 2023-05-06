CREATE TABLE TB_Schedule
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    date DATETIME NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_schedule_doctor_id FOREIGN KEY (doctor_id) REFERENCES TB_Doctor(id),
    CONSTRAINT fk_schedule_patient_id FOREIGN KEY (patient_id) REFERENCES TB_Patient(id)
);