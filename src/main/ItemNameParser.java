package main;

/**
 * Created by Administrator on 2014/12/18.
 */
public class ItemNameParser {
    public static String parse(String name) {
        return "item" + Integer.parseInt(name.substring(4));
    }
}
