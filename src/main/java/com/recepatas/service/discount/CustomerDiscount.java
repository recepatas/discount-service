package com.recepatas.service.discount;

import com.recepatas.model.Item;
import com.recepatas.model.User;
import com.recepatas.model.UserType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static com.recepatas.service.Constants.*;

@Component
public class CustomerDiscount implements Discount {

    public boolean isValid(User user) {
        return user.getUserType().equals(UserType.CUSTOMER) && isOldCustomer(user);
    }

    private boolean isOldCustomer(User user) {
        return ChronoUnit.YEARS.between(
                user.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now(ZoneId.systemDefault())
        ) >= CUSTOMER_DISCOUNT_AGE_THRESHOLD;
    }

    public BigDecimal calculate(Item item) {
        return item.getPrice().multiply(CUSTOMER_DISCOUNT_RATE);
    }
}
