package com.vending.terminal.core.model.proxy;

import org.puremvc.java.patterns.proxy.Proxy;

public class DataBaseProxy extends Proxy {
    public static String NAME = "DataBaseProxy";

    public DataBaseProxy() {
        super(NAME);
    }

    public String getPrice(String itemPosition) {
        return null;
    }
}
