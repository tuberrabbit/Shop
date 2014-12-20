package main;

import java.util.Set;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Item {
    private String barcode;
    private double price;
    private Set<String> labels;

    public Item() {
    }

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public Item(Item item) {
        this.barcode = item.getBarcode();
        this.price = item.getPrice();
    }

    public Item(String barcode, double price) {
        this.barcode = barcode;
        this.price = price;
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

    public void addLabel(String label) {
        labels.add(label);
    }

    public boolean hasLabel(String label) {
        return labels.contains(label);
    }
}
