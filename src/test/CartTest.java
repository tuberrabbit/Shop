package test;

import main.Cart;
import main.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2014/12/12.
 */
public class CartTest {
    private Cart cart;
    private Item item;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
        item = new Item("ITEM000005", 50);
    }

    @Test
    public void test_add_item_when_there_is_not_the_same_item_before() {
        cart.add(item, 2);

        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void test_add_item_when_there_is_the_same_item_before() throws Exception {
        cart.add(item, 2);
        cart.add(item, 3);

        assertEquals(1, cart.getItems().size());
    }

    @Test
    public void test_withdraw_item() throws Exception {
        cart.add(item, 2);

        assertThat(cart.withdraw(), is(item));
    }

    @Test
    public void test_remove_item() throws Exception {
        cart.add(item, 2);
        cart.remove(item);

        assertEquals(0, cart.getAmountOf(item));
    }

    @Test
    public void test_get_item_amount() throws Exception {
        cart.add(item, 2);

        assertEquals(2, cart.getAmountOf(item));

    }

    @Test
    public void should_return_true_when_add_item_more_than_0() throws Exception {
        boolean flag = cart.add(item, 1);

        assertThat(flag, is(true));
    }

    @Test
    public void should_return_false_when_add_item_less_or_equals_then_0() {
        boolean flag = cart.add(item, 0);

        assertThat(flag, is(false));
    }
}
