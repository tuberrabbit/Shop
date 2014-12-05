package com.Test;

import com.tuber.Cart;
import com.tuber.Pos;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Administrator on 2014/11/27.
 */
public class PosTest {
    @Before
    public void setUp() throws Exception {

    }

    Pos pos = new Pos();
    @Test
    public void testInitItemList() throws IOException {
        pos.initItemList();
        Map<String, Double> itemList = pos.getItemList();
        System.out.println("itemList:");
        for (Map.Entry<String, Double> entry : itemList.entrySet()) {
            System.out.printf("%s:%f\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testInitSecondHalfPricePromotionList() throws IOException {
        pos.initSecondHalfPricePromotionList();
        Set<String> secondHalfPricePromotionList = pos.getSecondHalfPricePromotionList();
        System.out.println("secondHalfPricePromotionList:");
        for (String entry : secondHalfPricePromotionList) {
            System.out.printf("%s\n", entry);
        }
    }

    @Test
    public void testInitDiscountPromotionList() throws IOException {
        pos.initDiscountPromotionList();
        Map<String, Double> discountPromotionList = pos.getDiscountPromotionList();
        System.out.println("discountPromotionList:");
        for (Map.Entry<String, Double> entry : discountPromotionList.entrySet()) {
            System.out.printf("%s:%f\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testInitCartList() throws IOException {
        pos.initCartList();
        Map<String, Integer> cartList = pos.getCartList();
        System.out.println("cartList:");
        for (Map.Entry<String, Integer> entry : cartList.entrySet()) {
            System.out.printf("%s:%d\n", entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testCalculate() throws IOException {
        pos.initAllTxtList();
        Cart cart = pos.calculate();
        cart.print();
    }

    @Test
    public void testPrintShoppingList() throws IOException {
        pos.initAllTxtList();
        Cart cart = pos.calculate();
        pos.printShoppingList(cart);
    }

    @After
    public void tearDown() throws Exception {

    }

}
