package test;

import main.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Administrator on 2014/12/11.
 */
public class PosTest {
    private Pos pos;
    @Before
    public void setUp() throws Exception {
        pos = new Pos();
    }

    @Test
    public void test_second_half_price_promotion() throws Exception {
        Cart cart = new Cart();
        Item item = new Item("ITEM000005", 30);
        Set<String> labels = new HashSet<String>();
        labels.add("second_half_price");
        item.setLabels(labels);
        cart.add(item, 3);
        BufferedReader secondHalfPriceReader = new BufferedReader(new FileReader("properties/second_half_price_promotion.txt"));
        SecondHalfPricePromotion.init(secondHalfPriceReader);

        Bill bill = pos.calculate(cart);

        System.out.println(bill.toString());

    }

    @Test
    public void test_discount_promotion() throws Exception {
        Cart cart = new Cart();
        Item item = new Item("ITEM000005", 30);
        Set<String> labels = new HashSet<String>();
        labels.add("discount");
        item.setLabels(labels);
        cart.add(item, 3);
        BufferedReader discountReader = new BufferedReader(new FileReader("properties/discount_promotion.txt"));
        DiscountPromotion.init(discountReader);

        Bill bill = pos.calculate(cart);

        System.out.println(bill.toString());
    }

    @Test
    public void should_return_bill_not_null_when_given_cart() {
        Cart cart = new Cart();

        Bill bill = pos.calculate(cart);

        assertNotNull(bill);
    }

    @Test
    public void should_return_bill_null_when_given_null() {
        Bill bill = pos.calculate(null);

        assertNull(bill);
    }
}
