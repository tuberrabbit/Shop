package main;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Administrator on 2014/11/25.
 */
public class Cart {
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
        if (items.containsKey(item)) {
            amount += items.get(item);
        }
        items.put(item, amount);
        return true;
    }
}
