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
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDiscountTest {

    @InjectMocks
    private EmployeeDiscount employeeDiscount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_true_when_user_is_an_employee() throws ParseException {
        User user = new User("user1", UserType.EMPLOYEE, new Date());
        Boolean result = employeeDiscount.isValid(user);
        assertTrue(result);
    }

    @Test
    public void should_return_false_when_user_is_not_an_employee() throws ParseException {
        User user = new User("user1", UserType.CUSTOMER, new Date());
        Boolean result = employeeDiscount.isValid(user);
        assertFalse(result);
    }

    @Test
    public void should_return_calculated_discount() throws ParseException {
        BigDecimal result = employeeDiscount.calculate(new Item("item1", ItemType.OTHER, BigDecimal.valueOf(123.50)));
        BigDecimal expected = BigDecimal.valueOf(37.05);
        assertEquals(expected, result);
    }

}
