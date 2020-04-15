package com.wgcisotto.patient.intake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ClinicCalendarTest {

    private ClinicCalendar calendar;

    @BeforeAll
    static void setup (){
        System.out.println("First");
    }

    @BeforeEach
    void init(){
        calendar = new ClinicCalendar(LocalDate.of(2018, 9, 1));
    }

    //package level
    @Test
    void AllowEntryOfAnAppointment(){
        calendar.addAppointment("William", "Cisotto", "avery",
                "09/01/2018 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAllAppointments();

        assertNotNull(appointments);
        assertEquals(1, appointments.size());

        PatientAppointment appointment = appointments.get(0);

        //Grouped related assertions to know all assertions failed at once.
        assertAll(
            () -> assertEquals("William", appointment.getPatientFirstName()),
            () -> assertEquals("Cisotto", appointment.getPatientLastName()),
            () -> assertEquals(Doctor.avery, appointment.getDoctor()),
            () -> assertSame(Doctor.avery, appointment.getDoctor()),
            () -> assertEquals("9/1/2018 02:00 PM",
                appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
        );

    }

    @Test
    void returnTrueForHasAppointmentsIfThereAppointments(){
        calendar.addAppointment("William", "Cisotto", "avery",
                "09/01/2018 2:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments(){
        assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnCurrentDaysAppointments(){
        calendar.addAppointment("William", "Cisotto", "avery",
                "09/01/2018 2:00 pm");
        calendar.addAppointment("William", "Cisotto", "avery",
                "09/01/2018 3:00 pm");
        //use this to compare list using Junit5
        assertIterableEquals(calendar.getAllAppointments(), calendar.getTodayAppointments());

        calendar.addAppointment("William", "Cisotto", "avery",
                "09/02/2018 2:00 pm");

        assertEquals(2, calendar.getTodayAppointments().size());
    }

    @Test
    void returnTomorrowAppointments(){
        calendar.addAppointment("William", "Cisotto", "avery",
                "09/01/2018 2:00 pm");
        calendar.addAppointment("William", "Cisotto", "avery",
                "09/01/2018 3:00 pm");
        //use this to compare list using Junit5
        assertIterableEquals(calendar.getAllAppointments(), calendar.getTodayAppointments());

        calendar.addAppointment("William", "Cisotto", "avery",
                "09/02/2018 2:00 pm");

        assertEquals(1, calendar.getTomorrowAppointments().size());

    }

    @Nested
    @DisplayName("return upcoming appointments")
    class UpcomingAppointments {

        @Test
        @DisplayName("as empty list when there are none")
        void whenThereAreNone() {
            List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
            assertEquals(0, appointments.size());
        }

        @Test
        @DisplayName("correctly when there are some in the past as well")
        void whenThereAreSomePastAndFuture() {
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "07/27/2017 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "07/27/2018 2:00 pm");
            calendar.addAppointment("Jim", "Weaver", "avery",
                    "08/27/2020 2:00 pm");
            assertEquals(1, calendar.getUpcomingAppointments().size());
        }

    }

    @AfterAll
    static void tearsDown(){
        System.out.println("Last");
    }


}