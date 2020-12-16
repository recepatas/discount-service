package com.recepatas.service.discount;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.recepatas.service.Constants.*;

@Component
public class TotalBillDiscountCalculator {

    public BigDecimal calculate(BigDecimal price) {

        int discountCount = price.divide(BILL_DISCOUNT_AMOUNT, RoundingMode.DOWN).toBigInteger().intValue();

        return BigDecimal.valueOf(discountCount).multiply(BILL_DISCOUNT);
    }

}
