package com.recepatas.service.discount;

import com.recepatas.model.Item;
import com.recepatas.model.User;
import com.recepatas.model.UserType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.recepatas.service.Constants.AFFILIATE_DISCOUNT_RATE;

@Component
public class AffiliateDiscount implements Discount {

    public boolean isValid(User user) {
        return user.getUserType().equals(UserType.AFFILIATE);
    }

    public BigDecimal calculate(Item item) {
        return item.getPrice().multiply(AFFILIATE_DISCOUNT_RATE);
    }
}
