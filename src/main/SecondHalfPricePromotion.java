package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2014/12/20.
 */
public class SecondHalfPricePromotion {
    private static Set<String> info;

    public static void init(BufferedReader reader) throws IOException {
        if (info != null) {
            return;
        }

        info = new HashSet<String>();
        while (reader.ready()) {
            String barcode = reader.readLine();
            info.add(barcode);
        }
    }

    public static double getReduce(double price, Integer amount) {
        return (amount / 2 * price / 2.);
    }

    public static boolean include(String barcode) {
        return info.contains(barcode);
    }
}
