package com.tuber;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Item {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private double priceBefore;

    public double getPriceBefore() {
        return priceBefore;
    }

    public void setPriceBefore(double priceBefore) {
        this.priceBefore = priceBefore;
    }

    private double priceAfter;

    public double getPriceAfter() {
        return priceAfter;
    }

    public void setPriceAfter(double priceAfter) {
        this.priceAfter = priceAfter;
    }

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private double totalBefore;

    public double getTotalBefore() {
        return totalBefore;
    }

    public void setTotalBefore(double totalBefore) {
        this.totalBefore = totalBefore;
    }

    private double totalAfter;

    public double getTotalAfter() {
        return totalAfter;
    }

    public void setTotalAfter(double totalAfter) {
        this.totalAfter = totalAfter;
    }

    public Item() {
    }

    public Item(Item item) {
        this.id = item.getId();
        this.amount = item.getAmount();
        this.priceBefore = item.getPriceBefore();
        this.priceAfter = item.getPriceAfter();
        this.totalAfter = item.getTotalAfter();
        this.totalBefore = item.getTotalBefore();
    }

}
