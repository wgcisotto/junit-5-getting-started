package com.wgcisotto.patient.intake;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void calculateCorrectly(){
       assertEquals(19.2, BMICalculator.calculateBmi(69, 130));
       assertEquals(21.52, BMICalculator.calculateBmi(70, 150));
    }

}