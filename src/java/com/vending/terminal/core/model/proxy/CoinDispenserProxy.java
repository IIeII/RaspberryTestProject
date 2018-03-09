package com.vending.terminal.core.model.proxy;

import org.puremvc.java.patterns.proxy.Proxy;

import java.math.BigDecimal;

public class CoinDispenserProxy extends Proxy {

    public static String NAME = "CoinDispenserProxy";

    public CoinDispenserProxy() {
        super(NAME);
    }


    public void payout(BigDecimal amountForPayout) {

    }
}
