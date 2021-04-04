package com.kingofnone.rentpentti.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.Month;
import java.time.Year;


public class MonthlyPaymentTest {

    @Test
    public void testBefore() {
        MonthlyPayment monthlyPayment1 = new MonthlyPayment(); // Jan 2019
        monthlyPayment1.setYear(Year.of(2019));
        monthlyPayment1.setMonth(Month.JANUARY);
        MonthlyPayment monthlyPayment2 = new MonthlyPayment(); // Feb 2020
        monthlyPayment2.setYear(Year.of(2020));
        monthlyPayment2.setMonth(Month.FEBRUARY);
        Assert.assertTrue("Compare years", monthlyPayment1.before(monthlyPayment2));

        monthlyPayment1.setYear(Year.of(2020)); // Jan 2020
        Assert.assertFalse("Compare when years are the same", monthlyPayment1.before(monthlyPayment2));

        monthlyPayment1.setMonth(Month.FEBRUARY);
        Assert.assertFalse("Compare same dates", monthlyPayment1.before(monthlyPayment2));
        Assert.assertFalse("Compare same dates", monthlyPayment2.before(monthlyPayment1));


    }
}
