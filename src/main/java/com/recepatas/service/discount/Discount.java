package com.recepatas.service.discount;

import com.recepatas.model.Item;
import com.recepatas.model.User;

import java.math.BigDecimal;

public interface Discount {

    public boolean isValid(User user);

    public BigDecimal calculate(Item item);

}
