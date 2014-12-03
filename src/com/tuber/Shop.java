package com.tuber;

import java.io.*;

public class Shop {
    public static void main(String[] args) throws IOException {
        Pos pos = new Pos();
        pos.initAllTxtList();
        Cart cart = pos.calculate();
        pos.printShoppingList(cart);
    }

}
