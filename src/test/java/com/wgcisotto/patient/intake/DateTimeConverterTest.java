package com.wgcisotto.patient.intake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("DateTimeConverter Test")
class DateTimeConverterTest {

    @Nested
    @DisplayName("convert string with 'today' keyword")
    class TodayTests{
        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly(){
            LocalDate today = LocalDate.of(2018, 9, 1);
            LocalDateTime result = DateTimeConverter.convertToDateFromString("today 1:00 pm",
                    today);
            assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0),
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
        }

        @Test
        @DisplayName("regardless of case")
        void convertTodayStringCorrectlyCaseInsensitive(){
            LocalDate today = LocalDate.of(2018, 9, 1);
            LocalDateTime result = DateTimeConverter.convertToDateFromString("ToDay 1:00 pm",
                    today);
            assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0),
                    () -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
        }


    }
    @Test
    //@Disabled
    @Tag("datetime") // used to test specifics tests of the same tag.
    @DisplayName("convert expected date time pattern in string correctly")
    void convertCorrectPatternToDateTime(){
        LocalDateTime result = DateTimeConverter.convertToDateFromString("9/1/2018 1:00 pm",
                LocalDate.of(2018, 9, 1));
        assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0));
    }

    @Test
    @DisplayName("throw exception if entered pattern of string incorrect")
    void throwExceptionIfIncorrectPatternProvided(){
        Throwable error = assertThrows(RuntimeException.class, () -> DateTimeConverter.convertToDateFromString("9/1/2018 100 pm",
                LocalDate.of(2018, 9, 1)));
        assertEquals("Unable to create date time from: [9/1/2018 100 PM], " +
                        "please enter with format [M/d/yyyy h:mm a], " +
                        "Text '9/1/2018 100 PM' could not be parsed at index 12",
                error.getMessage());
    }

}