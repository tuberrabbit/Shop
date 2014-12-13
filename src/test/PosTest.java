package test;

import main.Bill;
import main.Cart;
import main.Pos;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Administrator on 2014/12/11.
 */
public class PosTest {
    private Pos pos;
    @Before
    public void setUp() throws Exception {
        pos = new Pos();
    }

    @Test
    public void should_get_bill_when_calculate_cart() {
        Cart cart = new Cart();

        Bill bill = pos.calculate(cart);

        assertNotNull(bill);
    }

    @Test
    public void should_print_bill_when_get_bill() {
        Bill bill = new Bill();

        pos.print(bill);

    }
}
