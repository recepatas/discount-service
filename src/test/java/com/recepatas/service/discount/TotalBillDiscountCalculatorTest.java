package com.recepatas.service.discount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TotalBillDiscountCalculatorTest {

    @InjectMocks
    private TotalBillDiscountCalculator totalBillDiscountCalculator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_calculated_discount() {
        BigDecimal result = totalBillDiscountCalculator.calculate(BigDecimal.valueOf(100.00));
        BigDecimal expected = BigDecimal.valueOf(5.00);
        assertEquals(expected, result);
    }

    @Test
    public void should_return_calculated_discount_for_a_price_not_multiplier_of_100() {
        BigDecimal result = totalBillDiscountCalculator.calculate(BigDecimal.valueOf(495.00));
        BigDecimal expected = BigDecimal.valueOf(20.00);
        assertEquals(expected, result);
    }

    @Test
    public void should_return_calculated_discount_for_a_price_less_than_100() {
        BigDecimal result = totalBillDiscountCalculator.calculate(BigDecimal.valueOf(35.00));
        BigDecimal expected = BigDecimal.valueOf(0.00);
        assertEquals(expected, result);
    }

}
