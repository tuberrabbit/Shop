package com.tuber;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Cart {
    private TreeSet<Item> items;

    public TreeSet<Item> getItems() {
        return items;
    }

    public void add(Item item) {
        items.add(item);
        totalBefore += item.getTotalBefore();
        totalAfter += item.getTotalAfter();
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

    public void print() {
        for (Item item : items) {
            System.out.printf("%s:%d:%f\n", item.getId(), item.getAmount(), item.getPriceBefore());
        }
    }

    public Cart() {
        items = new TreeSet<Item>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return (o1.getId().compareTo(o2.getId()));
            }
        });
        totalBefore = 0;
        totalAfter = 0;
    }

}
