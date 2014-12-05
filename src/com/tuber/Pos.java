package com.tuber;

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
    private Map<String, Double> itemList;
    private Set<String> secondHalfPricePromotionList;
    private Map<String, Double> discountPromotionList;
    private Map<String, Integer> cartList;
    private Map<String, DiscountInfo> discountWhenFullPricePromotionList;

    public Map<String, DiscountInfo> getDiscountWhenFullPricePromotionList() {
        return discountWhenFullPricePromotionList;
    }

    public Map<String, Double> getItemList() {
        return itemList;
    }

    public Set<String> getSecondHalfPricePromotionList() {
        return secondHalfPricePromotionList;
    }

    public Map<String, Double> getDiscountPromotionList() {
        return discountPromotionList;
    }

    public Map<String, Integer> getCartList() {
        return cartList;
    }

    public void initAllTxtList() throws IOException {
        initItemList();
        initSecondHalfPricePromotionList();
        initDiscountPromotionList();
        initCartList();
        initDiscountWhenFullPricePromotion();
    }

    public void initDiscountWhenFullPricePromotion() throws IOException {
        discountWhenFullPricePromotionList = new HashMap<String, DiscountInfo>();
        BufferedReader br = new BufferedReader(new FileReader("properties/discount_when_full_price_promotion.txt"));
        String lineTxt;
        while (br.ready()) {
            lineTxt = br.readLine();
            String[] params = lineTxt.split(":");
            discountWhenFullPricePromotionList.put(params[0], new DiscountInfo(Double.parseDouble(params[1]), Double.parseDouble(params[2])/100.));
        }
    }

    public void initSecondHalfPricePromotionList() throws IOException {
        secondHalfPricePromotionList = new HashSet<String>();
        BufferedReader br = new BufferedReader(new FileReader("properties/second_half_price_promotion.txt"));
        String lineTxt;
        while ((lineTxt = br.readLine()) != null) {
            secondHalfPricePromotionList.add(lineTxt);
        }
    }

    public void initItemList() throws IOException {
        itemList = new HashMap<String, Double>();
        BufferedReader br = new BufferedReader(new FileReader("properties/itemlist.txt"));
        String lineTxt;
        while ((lineTxt = br.readLine()) != null) {
            String[] params = lineTxt.split(":");
            itemList.put(params[0], obtainPrice(params[1]));
        }
    }

    private double obtainPrice(String param) {
        return Double.parseDouble(param);
    }

    public void initDiscountPromotionList() throws IOException {
        discountPromotionList = new HashMap<String, Double>();
        BufferedReader br = new BufferedReader(new FileReader("properties/discount_promotion.txt"));
        String lineTxt;
        while ((lineTxt = br.readLine()) != null) {
            String[] params = lineTxt.split(":");
            discountPromotionList.put(params[0], obtainDiscount(params[1]));
        }
    }

    private double obtainDiscount(String param) {
        return obtainPrice(param) / 100.;
    }

    public void initCartList() throws IOException {
        cartList = new HashMap<String, Integer>();
        BufferedReader br = new BufferedReader(new FileReader("properties/cart.txt"));
        String lineTxt;
        while ((lineTxt = br.readLine()) != null) {
            String[] params = lineTxt.split("-");
            cartList.put(params[0], obtainAmount(params));
        }
    }

    private int obtainAmount(String[] params) {
        int amount;
        if (params.length < 2) {
            amount = 1;
        } else {
            amount = getParamAmount(params);
        }
        if (hasCartListContains(params[0])) {
            amount += getParamAmount(params[0]);
        }
        return amount;
    }

    private Integer getParamAmount(String param) {
        return cartList.get(param);
    }

    private boolean hasCartListContains(String param) {
        return cartList.containsKey(param);
    }

    private int getParamAmount(String[] params) {
        return Integer.parseInt(params[1]);
    }

    public Item secondHalfPricePromotion(Item item) {
        Item result = new Item(item);
        result.setTotalAfter(totalAfter_SecondHalfPrice(item));
        return result;
    }

    private double totalAfter_SecondHalfPrice(Item item) {
        return item.getTotalAfter() - (item.getAmount() / 2) * (item.getPriceAfter() / 2.);
    }

    public Item discountPromotion(Item item) {
        Item result = new Item(item);
        result.setPriceAfter(priceAfter_Discount(item));
        result.setTotalAfter(totalAfter_Discount(item));
        return result;
    }

    private double totalAfter_Discount(Item item) {
        return item.getTotalAfter() * discountPromotionList.get(item.getId());
    }

    private double priceAfter_Discount(Item item) {
        return item.getPriceAfter() * discountPromotionList.get(item.getId());
    }

    private boolean isDiscountPromotion(Item item) {
        return (discountPromotionList.containsKey(item.getId()));
    }

    private boolean isSecondHalfPricePromotion(Item item) {
        return (secondHalfPricePromotionList.contains(item.getId()));
    }

    private Item purchaseItem(Map.Entry<String, Integer> entry) {
        Item result = new Item();
        result.setId(entry.getKey());
        result.setAmount(entry.getValue());
        result.setPriceBefore(itemList.get(entry.getKey()));
        result.setPriceAfter(result.getPriceBefore());
        result.setTotalBefore(result.getPriceBefore() * result.getAmount());
        result.setTotalAfter(result.getTotalBefore());
        return result;
    }

    public Cart calculate() {
        Cart result = new Cart();
        for (Map.Entry<String, Integer> entry : cartList.entrySet()) {
            Item item = purchaseItem(entry);
            if (isSecondHalfPricePromotion(item)) {
                item = secondHalfPricePromotion(item);
            }
            if (isDiscountPromotion(item)) {
                item = discountPromotion(item);
            }
            result.add(item);
        }
        return result;
    }

    public void printShoppingList(Cart cart) {
        System.out.println("购物明细（数量\t单价\t小计）");
        for (Item item : cart.getItems()) {
            System.out.printf("%-10s%-4d\t%-4.0f\t%-10.0f\n", toItemName(item.getId()), item.getAmount(), item.getPriceBefore(), item.getTotalAfter());
        }
        System.out.println("总计金额（优惠前\t优惠后\t优惠差价）");
        System.out.printf("%-10.0f%-6.0f\t%-6.0f\t%-10.0f\n", cart.getTotalAfter(), cart.getTotalBefore(), cart.getTotalAfter(), cart.getTotalBefore() - cart.getTotalAfter());
    }

    private String toItemName(String id) {
        return "item" + Integer.parseInt(id.substring(4));
    }
}
