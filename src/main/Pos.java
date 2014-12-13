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
    private Map<String, Double> itemList;
    private Set<String> secondHalfPricePromotionList;
    private Map<String, Double> discountPromotionList;
    private Map<String, Integer> cartList;
//    private Map<String, DiscountInfo> discountWhenFullPricePromotionList;

//    public Map<String, DiscountInfo> getDiscountWhenFullPricePromotionList() {
//        return discountWhenFullPricePromotionList;
//    }

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
//        initDiscountPromotionList();
//        initCartList();
//        initDiscountWhenFullPricePromotion();
    }

//    public void initDiscountWhenFullPricePromotion() throws IOException {
//        discountWhenFullPricePromotionList = new HashMap<String, DiscountInfo>();
//        BufferedReader br = new BufferedReader(new FileReader("properties/discount_when_full_price_promotion.txt"));
//        String lineTxt;
//        while (br.ready()) {
//            lineTxt = br.readLine();
//            String[] params = lineTxt.split(":");
//            discountWhenFullPricePromotionList.put(params[0], new DiscountInfo(Double.parseDouble(params[1]), Double.parseDouble(params[2])/100.));
//        }
//    }

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

    public void initDiscountPromotionList(BufferedReader br) throws IOException {
        discountPromotionList = new HashMap<String, Double>();
        String lineTxt;
        while ((lineTxt = br.readLine()) != null) {
            String[] params = lineTxt.split(":");
            discountPromotionList.put(params[0], obtainDiscount(params[1]));
        }
    }

    private double obtainDiscount(String param) {
        return obtainPrice(param) / 100.;
    }

    public void initCartList(BufferedReader br) throws IOException {
        cartList = new HashMap<String, Integer>();
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

    public Bill calculate(Cart cart) {
        Bill bill = new Bill();
        for (Map.Entry<Item, Integer> entry : cart.getItems().entrySet()) {
            bill.add(entry.getKey(), entry.getValue());
        }
        return bill;
    }

    private String toItemName(String id) {
        return "item" + Integer.parseInt(id.substring(4));
    }

    public void print(Bill bill) {
        System.out.println("购物明细（数量\t单价\t小计）");
        for (Map.Entry<Item, Integer> entry : bill.getItems().entrySet()) {
            System.out.printf("%-10s%-4d\t%-4.0f\t%-10.0f\n", toItemName(getItemBarcode(entry)), getItemAmount(entry), getItemPrice(entry), getItemTotalPrice(entry));
        }
        System.out.println("总计金额（优惠前\t优惠后\t优惠差价）");
        System.out.printf("%-10.0f%-6.0f\t%-6.0f\t%-10.0f\n", bill.getPricePromotion(), bill.getPrice(), bill.getPricePromotion(), bill.getPrice() - bill.getPricePromotion());
    }

    private Integer getItemAmount(Map.Entry<Item, Integer> entry) {
        return entry.getValue();
    }

    private double getItemPrice(Map.Entry<Item, Integer> entry) {
        return entry.getKey().getPrice();
    }

    private String getItemBarcode(Map.Entry<Item, Integer> entry) {
        return entry.getKey().getBarcode();
    }

    private double getItemTotalPrice(Map.Entry<Item, Integer> entry) {
        return getItemPrice(entry) * getItemAmount(entry);
    }
}
