package test;

import main.Bill;
import main.Item;
import main.Record;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2014/12/12.
 */
public class BillTest {
    private Bill bill;
    private Item item;

    @Before
    public void setUp() throws Exception {
        bill = new Bill();
        item = new Item();
    }

    @Test
    public void test_toString() {
        Record record = new Record();
        record.setName("item1");
        record.setAmount(5);
        record.setPrice(40);
        record.setTotal(120);
        bill.getRecords().add(record);
        bill.setOriginalTotal(200);
        bill.setPromotionTotal(120);

        System.out.println(bill.toString());
    }
}
