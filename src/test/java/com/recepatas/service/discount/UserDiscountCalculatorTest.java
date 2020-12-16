package com.recepatas.service.discount;

import com.recepatas.model.Item;
import com.recepatas.model.ItemType;
import com.recepatas.model.User;
import com.recepatas.model.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDiscountCalculatorTest {

    @Mock
    private EmployeeDiscount employeeDiscount;

    @Mock
    private AffiliateDiscount affiliateDiscount;

    @Mock
    private CustomerDiscount customerDiscount;

    @InjectMocks
    private UserDiscountCalculator userDiscountCalculator;

    @Spy
    private List<Discount> discounts = new ArrayList<Discount>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        discounts.add(employeeDiscount);
        discounts.add(affiliateDiscount);
        discounts.add(customerDiscount);
    }

    @Test
    public void should_return_calculated_discount_for_given_item_and_user() {
        User user = new User("user1", UserType.EMPLOYEE, new Date());

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));

        when(employeeDiscount.isValid(user)).thenReturn(true);
        when(employeeDiscount.calculate(item1)).thenReturn(BigDecimal.valueOf(30.00));

        BigDecimal result = userDiscountCalculator.calculate(item1, user);
        BigDecimal expected = BigDecimal.valueOf(30.00);
        assertEquals(expected, result);

        verify(affiliateDiscount, never()).calculate(item1);
        verify(customerDiscount, never()).calculate(item1);
    }

    @Test
    public void should_return_zore_when_there_is_no_valid_discount_for_user() {
        User user = new User("user1", UserType.CUSTOMER, new Date());

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));

        when(employeeDiscount.isValid(user)).thenReturn(false);
        when(affiliateDiscount.isValid(user)).thenReturn(false);
        when(customerDiscount.isValid(user)).thenReturn(false);

        BigDecimal result = userDiscountCalculator.calculate(item1, user);
        BigDecimal expected = BigDecimal.ZERO;
        assertEquals(expected, result);

        verify(employeeDiscount, never()).calculate(item1);
        verify(affiliateDiscount, never()).calculate(item1);
        verify(customerDiscount, never()).calculate(item1);
    }

    @Test
    public void should_return_zero_for_item_type_is_grocery() {
        User user = new User("user1", UserType.EMPLOYEE, new Date());

        Item item1 = new Item("item1", ItemType.GROCERY, new BigDecimal(100));

        BigDecimal result = userDiscountCalculator.calculate(item1, user);
        BigDecimal expected = BigDecimal.ZERO;
        assertEquals(expected, result);

        verify(employeeDiscount, never()).calculate(item1);
        verify(affiliateDiscount, never()).calculate(item1);
        verify(customerDiscount, never()).calculate(item1);
    }

}
