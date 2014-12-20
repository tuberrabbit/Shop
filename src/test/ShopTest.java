package test;

import main.Cart;
import main.Item;
import main.Shop;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ShopTest {

    private Shop shop;

    @Before
    public void setUp() throws Exception {
        shop = new Shop();

    }

    @Test
    public void should_init_item_list_from_file() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("properties/itemlist.txt"));

        shop.initItemList(br);

        assertNotNull(shop.getItemList());
    }

    @Test
    public void should_get_null_when_file_is_not_arrange() throws Exception {
        Cart cart = shop.getCart(null);

        assertNull(cart);

    }

    @Test
    public void should_get_cart_from_file() throws Exception {
        Set<Item> itemList = new HashSet<Item>();
        Item item = new Item("ITEM000001", 40);
        itemList.add(item);
        item = new Item("ITEM000003", 50);
        itemList.add(item);
        item = new Item("ITEM000005", 60);
        itemList.add(item);
        shop.setItemList(itemList);
        BufferedReader br = new BufferedReader(new FileReader("properties/cart.txt"));

        Cart cart = shop.getCart(br);

        assertNotNull(cart);
    }
}