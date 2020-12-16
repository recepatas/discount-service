package com.recepatas;

import com.recepatas.model.*;
import com.recepatas.service.DiscountService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class App {

    public static void main( String[] args ) throws ParseException {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        DiscountService discountService = ctx.getBean(DiscountService.class);

        User user = new User("user1", UserType.CUSTOMER, new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01"));

        Item item1 = new Item("item1", ItemType.OTHER, new BigDecimal(100));
        Item item2 = new Item("item2", ItemType.OTHER, new BigDecimal(200));
        Item item3 = new Item("item3", ItemType.GROCERY, new BigDecimal(200));

        Bill bill = new Bill(user, Arrays.asList(item1, item2, item3));

        System.out.println( "Total Net Price: " + discountService.getNetPrice(bill));
    }
}
