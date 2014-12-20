package main;

/**
 * Created by Administrator on 2014/12/17.
 */
public class Record {
    private String name;
    private int amount;
    private double price;
    private double total;

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }
}
