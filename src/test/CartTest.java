package test;

import main.Cart;
import main.Item;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
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
        item = new Item();
    }

    @Test
    public void should_return_true_when_add_item_more_then_0() {

        boolean flag = cart.add(item, 1);

        assertThat(flag, is(true));
    }

    @Test
    public void should_return_false_when_add_item_less_or_equals_then_0() {

        boolean flag = cart.add(item, 0);

        assertThat(flag, is(false));
    }
}
