package main;

/**
 * Created by Administrator on 2014/12/12.
 */
public class DiscountPromotion {
    public boolean hasPromorion(Item item) {
        return true;
    }

    public double promotion(Item item, int amount) {
        double res = item.getPrice()*0.9*amount;

        return res;
    }
}
