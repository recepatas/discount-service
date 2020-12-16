package com.recepatas.service;

import com.recepatas.model.*;
import com.recepatas.service.discount.TotalBillDiscountCalculator;
import com.recepatas.service.discount.UserDiscountCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    @Mock
    private UserDiscountCalculator userDiscountCalculator;

    @Mock
    private TotalBillDiscountCalculator totalBillDiscountCalculator;

    @InjectMocks
    private DiscountService discountService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_net_price_for_old_customer() throws ParseException {
        User user = new User("user1", UserType.CUSTOMER, new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01"));

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));
        Item item2 = new Item("item2", ItemType.OTHER, new BigDecimal(200));
        Item item3 = new Item("item3", ItemType.GROCERY, new BigDecimal(200));

        Bill bill = new Bill(user, Arrays.asList(item1, item2, item3));

        when(userDiscountCalculator.calculate(item1, user)).thenReturn(BigDecimal.valueOf(5));
        when(userDiscountCalculator.calculate(item2, user)).thenReturn(BigDecimal.valueOf(10));
        when(userDiscountCalculator.calculate(item3, user)).thenReturn(BigDecimal.valueOf(0));

        when(totalBillDiscountCalculator.calculate(BigDecimal.valueOf(485))).thenReturn(BigDecimal.valueOf(20));

        BigDecimal result = discountService.getNetPrice(bill);
        double expected = 465.00;

        assertEquals(465.00, result.doubleValue(), 0);
    }

}
