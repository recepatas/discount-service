package com.recepatas.service;

import com.recepatas.model.Bill;
import com.recepatas.service.discount.TotalBillDiscountCalculator;
import com.recepatas.service.discount.UserDiscountCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DiscountService {

    @Autowired
    private UserDiscountCalculator userDiscountCalculator;

    @Autowired
    private TotalBillDiscountCalculator totalBillDiscountCalculator;

    public BigDecimal getNetPrice(Bill bill) {

        BigDecimal totalAmountWithDiscount = bill.getItems().stream()
                .map(item -> item.getPrice()
                        .subtract(userDiscountCalculator.calculate(item, bill.getUser()))
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalAmountWithDiscount.subtract(totalBillDiscountCalculator.calculate(totalAmountWithDiscount)).setScale(2, RoundingMode.UP);
    }

}
