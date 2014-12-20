package main;

import java.util.*;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Cart {
    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    private Map<Item, Integer> items;

    public Map<Item, Integer> getItems() {
        return items;
    }

    public Cart() {
        items = new HashMap<Item, Integer>();
    }

    public boolean add(Item item, int amount) {
        if (amount<1) {
            return false;
        }

        if (hasContains(item)) {
            amount += getAmountOf(item);
            remove(item);
        }
        items.put(item, amount);

        return true;
    }

    public void remove(Item key) {
        for (Item item : items.keySet()) {
            if (item.getBarcode().equals(key.getBarcode())) {
                items.remove(item);
                return;
            }
        }
    }

    private boolean hasContains(Item key) {
        for (Item item : items.keySet()) {
            if (item.getBarcode().equals(key.getBarcode())) {
                return true;
            }
        }
        return false;
    }

    public Item withdraw() {
        Item item = null;
        if (!items.isEmpty()) {
            item = items.keySet().iterator().next();
        }

        return item;
    }

    public int getAmountOf(Item key) {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            if (entry.getKey().getBarcode().equals(key.getBarcode())) {
                return entry.getValue();
            }
        }
        return 0;
    }
}
