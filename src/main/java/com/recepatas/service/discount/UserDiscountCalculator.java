package com.recepatas.service.discount;

import com.recepatas.model.Item;
import com.recepatas.model.ItemType;
import com.recepatas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class UserDiscountCalculator {

    @Autowired
    private List<Discount> discounts;

    public BigDecimal calculate(Item item, User user) {

        if(item.getType().equals(ItemType.GROCERY)) {
            return BigDecimal.ZERO;
        }

        for (Discount discount: discounts) {
            if(discount.isValid(user)) {
                return discount.calculate(item);
            }
        }
        return BigDecimal.ZERO;
    }
}
