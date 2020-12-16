package com.recepatas.service;

import com.recepatas.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DiscountServiceIntegrationTest {

    @Autowired
    DiscountService discountService;

    @Test
    public void should_return_net_price_for_old_customer() throws ParseException {
        User user = new User("user1", UserType.CUSTOMER, new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01"));

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));
        Item item2 = new Item("item2", ItemType.OTHER, new BigDecimal(200));
        Item item3 = new Item("item3", ItemType.GROCERY, new BigDecimal(200));

        Bill bill = new Bill(user, Arrays.asList(item1, item2, item3));

        BigDecimal result = discountService.getNetPrice(bill);

        assertEquals(465.00, result.doubleValue(), 0);
    }

    @Test
    public void should_return_net_price_for_new_customer()  {
        User user = new User("user1", UserType.CUSTOMER, new Date());

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));
        Item item2 = new Item("item2", ItemType.OTHER, new BigDecimal(200));
        Item item3 = new Item("item3", ItemType.GROCERY, new BigDecimal(200));

        Bill bill = new Bill(user, Arrays.asList(item1, item2, item3));

        BigDecimal result = discountService.getNetPrice(bill);

        assertEquals(475.00, result.doubleValue(), 0);
    }

    @Test
    public void should_return_net_price_for_employee() throws ParseException {
        User user = new User("user1", UserType.EMPLOYEE, new Date());

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100).setScale(2));
        Item item2 = new Item("item2", ItemType.OTHER, new BigDecimal(200).setScale(2));
        Item item3 = new Item("item3", ItemType.GROCERY, new BigDecimal(200).setScale(2));

        Bill bill = new Bill(user, Arrays.asList(item1, item2, item3));

        BigDecimal result = discountService.getNetPrice(bill);

        assertEquals(390.00, result.doubleValue(), 0);
    }

    @Test
    public void should_return_net_price_for_affiliate() throws ParseException {
        User user = new User("user1", UserType.AFFILIATE, new Date());

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));
        Item item2 = new Item("item2", ItemType.OTHER, new BigDecimal(200));
        Item item3 = new Item("item3", ItemType.GROCERY, new BigDecimal(200));

        Bill bill = new Bill(user, Arrays.asList(item1, item2, item3));

        BigDecimal result = discountService.getNetPrice(bill);

        assertEquals(450.00, result.doubleValue(), 0);
    }

}
