package main;

import java.text.Format;
import java.util.*;

/**
 * Created by Administrator on 2014/12/11.
 */
public class Bill {
    private double promotionTotal;
    private double originalTotal;
    private Set<Record> records;

    public Bill() {
        records = new TreeSet<Record>(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        originalTotal = 0;
        promotionTotal = 0;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("购物明细（数量\t单价\t小计）\n");
        for (Record record : records) {
            buf.append(String.format("%-10s%-6d%-8.0f%-6.0f\n", ItemNameParser.parse(record.getName()), record.getAmount(), record.getPrice(), record.getTotal()));
        }
        buf.append("总计金额（优惠前\t优惠后\t优惠差价）\n");
        buf.append(String.format("%-10.0f%-6.0f\t%-6.0f\t%-10.0f\n", promotionTotal, originalTotal, promotionTotal, originalTotal - promotionTotal));
        return buf.toString();
    }

    public void setPromotionTotal(double promotionTotal) {
        this.promotionTotal = promotionTotal;
    }

    public double getPromotionTotal() {
        return promotionTotal;
    }

    public void setOriginalTotal(double originalTotal) {
        this.originalTotal = originalTotal;
    }

    public double getOriginalTotal() {
        return originalTotal;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public void add(Record record) {
        records.add(record);
        this.originalTotal += record.getPrice()*record.getAmount();
        this.promotionTotal += record.getTotal();
    }
}
