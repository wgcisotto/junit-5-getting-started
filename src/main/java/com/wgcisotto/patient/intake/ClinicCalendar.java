package com.wgcisotto.patient.intake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ClinicCalendar {

    private List<PatientAppointment> appointments;
    private LocalDate today;

    public ClinicCalendar(LocalDate today) {
        this.today = today;
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(String patientFirstName, String patientLasttName, String doctorKey,
                                String dateTime){

        Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime = DateTimeConverter.convertToDateFromString(dateTime, today);
        PatientAppointment patientAppointment = new PatientAppointment(patientFirstName, patientLasttName, localDateTime, doc);
        appointments.add(patientAppointment);

    }

    public List<PatientAppointment> getAllAppointments(){
        return appointments;
    }

    public List<PatientAppointment> getTodayAppointments(){
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today))
                .collect(Collectors.toList());
    }

    public List<PatientAppointment> getTomorrowAppointments() {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today.plusDays(1)))
                .collect(Collectors.toList());
    }

    public List<PatientAppointment> getUpcomingAppointments() {
        return appointments.stream()
                .filter(appt -> appt.getAppointmentDateTime().toLocalDate().isAfter(today))
                .collect(Collectors.toList());
    }

    public boolean hasAppointment(LocalDate date){
        return appointments.stream()
                .anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
    }
}
