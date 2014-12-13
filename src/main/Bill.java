package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/11.
 */
public class Bill {
    private Map<Item, Integer> items;
    private double price;
    private double pricePromotion;

    public double getPrice() {
        return price;
    }

    public double getPricePromotion() {
        return pricePromotion;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public Bill() {
        items = new HashMap<Item, Integer>();
        price = 0;
        pricePromotion = 0;
    }

    public boolean add(Item item, int amount) {
        if (amount<1) {
            return false;
        }
        price += item.getPrice()*amount;
//        DiscountPromotion promotion = new DiscountPromotion();
//        if (promotion.hasPromorion(item)) {
//            pricePromotion += promotion.promotion(item, amount);
//        } else {
            pricePromotion += item.getPrice()*amount;
//        }
        if (items.containsKey(item)) {
            amount += items.get(item);
        }
        items.put(item, amount);
        return true;
    }
}
