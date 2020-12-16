package com.recepatas.service.discount;

import com.recepatas.model.Item;
import com.recepatas.model.User;
import com.recepatas.model.UserType;
import org.springframework.stereotype.Component;

import static com.recepatas.service.Constants.*;

import java.math.BigDecimal;

@Component
public class EmployeeDiscount implements Discount {

    public boolean isValid(User user) {
        return user.getUserType().equals(UserType.EMPLOYEE);
    }

    public BigDecimal calculate(Item item) {
        return item.getPrice().multiply(EMPLOYEE_DISCOUNT_RATE);
    }
}
