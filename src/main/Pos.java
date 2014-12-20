package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Pos {
    public Bill calculate(Cart cart) {
        if (cart == null) {
            return null;
        }

        Bill bill = new Bill();
        for (Map.Entry<Item, Integer> entry : cart.getItems().entrySet()) {
            double factor = 1;
            if (entry.getKey().hasLabel("discount")) {
                factor = DiscountPromotion.getDiscount(entry.getKey());
            }
            double reduction = 0;
            if (entry.getKey().hasLabel("second_half_price")) {
                reduction = SecondHalfPricePromotion.getReduce(entry.getKey().getPrice(), entry.getValue());
            }
            Record record = new Record();
            record.setName(entry.getKey().getBarcode());
            record.setAmount(entry.getValue());
            record.setPrice(entry.getKey().getPrice());
            record.setTotal(entry.getKey().getPrice() * entry.getValue() * factor - reduction * factor);
            bill.add(record);
        }
        return bill;
    }

}
