package main;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Item {
    private String barcode;
    private double price;

    public Item() {
    }

    public Item(Item item) {
        this.barcode = item.getBarcode();
        this.price = item.getPrice();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
