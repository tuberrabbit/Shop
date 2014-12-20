package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/19.
 */
public class DiscountPromotion {
    private static Map<String, Double> info;

    public static void init(BufferedReader reader) throws IOException {
        if (info != null) {
            return;
        }

        info = new HashMap<String, Double>();
        while (reader.ready()) {
            String line = reader.readLine();
            String[] params = line.split(":");
            String barcode = params[0];
            double factor = Double.parseDouble(params[1])/100.;
            info.put(barcode, factor);
        }
    }

    public static double getDiscount(Item key) {
        if (info.containsKey(key.getBarcode())) {
            return info.get(key.getBarcode());
        }
        return 1;
    }

    public static boolean include(String barcode) {
        return info.containsKey(barcode);
    }
}
