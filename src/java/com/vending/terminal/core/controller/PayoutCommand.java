package com.vending.terminal.core.controller;

import com.vending.terminal.core.model.proxy.BalanceManagerProxy;
import com.vending.terminal.core.model.proxy.CoinDispenserProxy;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class PayoutCommand extends SimpleCommand {

    @Override
    public void execute(INotification notification) {
        log("PayoutCommand is executing");

        BalanceManagerProxy balanceManagerProxy = (BalanceManagerProxy) facade.retrieveProxy(BalanceManagerProxy.NAME);
        CoinDispenserProxy coinDispenserProxy = (CoinDispenserProxy) facade.retrieveProxy(CoinDispenserProxy.NAME);

        coinDispenserProxy.payout(balanceManagerProxy.getRegularBalance());

        balanceManagerProxy.resetBalance();
    }
}
