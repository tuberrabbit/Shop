package main;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2014/12/18.
 */
public class Shop {
    private Set<Item> itemList;

    public Set<Item> getItemList() {
        return itemList;
    }

    public void setItemList(Set<Item> itemList) {
        this.itemList = itemList;
    }

    public void initItemList(BufferedReader br) throws IOException {
        if (br == null) {
            return;
        }

        itemList = new HashSet<Item>();
        while (br.ready()) {
            String line = br.readLine();
            String[] params = line.split(":");
            Item item = new Item(params[0], Double.parseDouble(params[1]));
            itemList.add(item);
        }
    }

    public Cart getCart(BufferedReader br) throws IOException {
        if (br == null) {
            return null;
        }

        Cart cart = new Cart();
        while (br.ready()) {
            String line = br.readLine();
            String barcode;
            int amount;
            if (line.length() < 11) {
                barcode = line;
                amount = 1;
            } else {
                barcode = line.substring(0, 10);
                amount = Integer.parseInt(line.substring(11));
            }
            double price = 0;
            for (Item item : itemList) {
                if (item.getBarcode().equals(barcode)) {
                    price = item.getPrice();
                    break;
                }
            }
            Item item = new Item(barcode, price);
            Set<String> labels = new HashSet<String>();
            if (DiscountPromotion.include(item.getBarcode())) {
                labels.add("discount");
            }
            if (SecondHalfPricePromotion.include(item.getBarcode())) {
                labels.add("second_half_price");
            }
            item.setLabels(labels);
            cart.add(item, amount);
        }

        return cart;
    }

    public static void main(String[] asdf) throws IOException {
        Shop shop = new Shop();
        BufferedReader itemListReader = new BufferedReader(new FileReader("properties/itemlist.txt"));
        shop.initItemList(itemListReader);
        BufferedReader shoppingListReader = new BufferedReader(new FileReader("properties/cart.txt"));
        BufferedReader secondHalfPriceReader = new BufferedReader(new FileReader("properties/second_half_price_promotion.txt"));
        SecondHalfPricePromotion.init(secondHalfPriceReader);
        BufferedReader discountReader = new BufferedReader(new FileReader("properties/discount_promotion.txt"));
        DiscountPromotion.init(discountReader);
        Cart cart = shop.getCart(shoppingListReader);
        Pos pos = new Pos();
        Bill bill = pos.calculate(cart);
        System.out.println(bill.toString());
    }
}
