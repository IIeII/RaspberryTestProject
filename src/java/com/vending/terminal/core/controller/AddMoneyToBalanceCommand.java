package com.vending.terminal.core.controller;

import com.vending.terminal.core.config.MoneyTypeEnum;
import com.vending.terminal.core.model.proxy.BalanceManagerProxy;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class AddMoneyToBalanceCommand extends SimpleCommand {

    @Override
    public void execute(INotification notification) {

        log("AddMoneyToBalanceCommand is executing");

        notification.getBody();

        BalanceManagerProxy balanceManagerProxy = (BalanceManagerProxy) facade.retrieveProxy(BalanceManagerProxy.NAME);

        switch (notification.getType()){
            case MoneyTypeEnum.BONUS_MONEY:
                balanceManagerProxy.addToBonusBalance((String) notification.getBody());
                break;
            case MoneyTypeEnum.REAL_MONEY:
                balanceManagerProxy.addToRegularBalance((String) notification.getBody());
                break;
            default:
                err("AddMoneyToBalanceCommand received unknown type of money in notification.getType()");
                break;
        }
    }
}
