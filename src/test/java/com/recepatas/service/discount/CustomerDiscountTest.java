package com.recepatas.service.discount;

import com.recepatas.model.Item;
import com.recepatas.model.ItemType;
import com.recepatas.model.User;
import com.recepatas.model.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDiscountTest {

    @InjectMocks
    private CustomerDiscount customerDiscount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_true_when_user_is_an_old_customer() throws ParseException {
        User user = new User("user1", UserType.CUSTOMER, new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01"));
        Boolean result = customerDiscount.isValid(user);
        assertTrue(result);
    }

    @Test
    public void should_return_false_when_user_is_not_an_old_affiliate() {
        User user = new User("user1", UserType.CUSTOMER, new Date());
        Boolean result = customerDiscount.isValid(user);
        assertFalse(result);
    }

    @Test
    public void should_return_calculated_discount() {
        BigDecimal result = customerDiscount.calculate(new Item("item1", ItemType.OTHER, BigDecimal.valueOf(123.50)));
        BigDecimal expected = BigDecimal.valueOf(6.175);
        assertEquals(expected, result);
    }

}
