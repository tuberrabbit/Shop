package com.tuber;

/**
 * Created by tuber on 14-12-5.
 */
public class DiscountInfo {
    private double price;
    private double discount;

    public DiscountInfo(double price, double discount) {
        this.price = price;
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
