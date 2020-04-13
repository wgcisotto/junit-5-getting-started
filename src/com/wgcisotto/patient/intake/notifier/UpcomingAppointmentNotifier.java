package com.wgcisotto.patient.intake.notifier;

import com.wgcisotto.patient.intake.ClinicCalendar;
import com.wgcisotto.patient.intake.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UpcomingAppointmentNotifier {

    private ClinicCalendar calendar;
    private EmailNotifier notifier;

    public UpcomingAppointmentNotifier(ClinicCalendar calendar, EmailNotifier notifier){
        this.calendar = calendar;
        this.notifier = notifier;
    }

    public void run(){
        calendar.getTomorrowAppointments().forEach(this::sendNotificationForAppointments);
    }

    private void sendNotificationForAppointments(PatientAppointment appt) {
        String email = appt.getPatientLastName().toLowerCase() + appt.getPatientFirstName().toLowerCase() + "@mail.com";
        final String emailBody = buildMessageBody(appt);
        System.out.println("Sending with body: " + emailBody);
        notifier.sendNotification("Appointment Reminder", emailBody, email);
    }

    private String buildMessageBody(PatientAppointment appt) {
        return new StringBuilder("You have an appointment tomorrow at " )
                .append(appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US)))
                .append(" with Dr. ")
                .append(appt.getDoctor().getName())
                .append(".").toString();
    }
}
