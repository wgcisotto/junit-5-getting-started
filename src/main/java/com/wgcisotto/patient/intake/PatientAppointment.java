package com.wgcisotto.patient.intake;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PatientAppointment {

    public PatientAppointment(String patientFirstName, String patientLastName, LocalDateTime appointmentDateTime, Doctor doctor) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
    }

    private String patientFirstName;
    private String patientLastName;
    private LocalDateTime appointmentDateTime;
    private Doctor doctor;
    private double appBmi;

}
