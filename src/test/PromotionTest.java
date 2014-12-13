package test;

import main.DiscountPromotion;
import main.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 2014/12/12.
 */
public class PromotionTest {
    private Item item;
    private int amount;

    @Before
    public void setUp() throws Exception {
        item = new Item();
        item.setBarcode("ITEM000005");
        item.setPrice(100);
        amount = 2;
    }

    @Test
    public void test_discount_promotion() {
        double pricePromotion = 0;
        DiscountPromotion promotion = new DiscountPromotion();
        if (promotion.hasPromorion(item)) {
            pricePromotion = promotion.promotion(item, amount);
        }

        assertTrue(pricePromotion<item.getPrice()*amount);
    }

}
