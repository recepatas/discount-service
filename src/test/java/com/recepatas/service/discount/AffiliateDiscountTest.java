package com.recepatas.service.discount;

import com.recepatas.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AffiliateDiscountTest {

    @InjectMocks
    private AffiliateDiscount affiliateDiscount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_true_when_user_is_an_affiliate() {
        Boolean result = affiliateDiscount.isValid(new User("user1", UserType.AFFILIATE, new Date()));
        assertTrue(result);
    }

    @Test
    public void should_return_false_when_user_is_not_an_affiliate() {
        Boolean result = affiliateDiscount.isValid(new User("user1", UserType.CUSTOMER, new Date()));
        assertFalse(result);
    }

    @Test
    public void should_return_calculated_discount() {
        BigDecimal result = affiliateDiscount.calculate(new Item("item1", ItemType.OTHER, BigDecimal.valueOf(123.50)));
        BigDecimal expected = BigDecimal.valueOf(12.35);
        assertEquals(expected, result);
    }

}
