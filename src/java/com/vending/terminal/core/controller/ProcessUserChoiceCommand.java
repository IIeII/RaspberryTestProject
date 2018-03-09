package com.vending.terminal.core.controller;

import com.vending.terminal.core.config.GeneralNotifications;
import com.vending.terminal.core.model.proxy.BalanceManagerProxy;
import com.vending.terminal.core.model.proxy.DataBaseProxy;
import com.vending.terminal.core.model.proxy.DoorsAndCoverProxy;
import com.vending.terminal.core.model.proxy.MotorsProxy;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class ProcessUserChoiceCommand extends SimpleCommand {

    @Override
    public void execute(INotification notification) {

        String itemPosition = (String) notification.getBody();

        DataBaseProxy dataBaseProxy = (DataBaseProxy) facade.retrieveProxy(DataBaseProxy.NAME);
        BalanceManagerProxy balanceManagerProxy = (BalanceManagerProxy) facade.retrieveProxy(BalanceManagerProxy.NAME);

        String price = dataBaseProxy.getPrice(itemPosition);
        if (!balanceManagerProxy.isEnoughMoneyForItem(price)){
            log("User trying to buy item: " + itemPosition + " with price: " + price + " but has not enough of money. His current balance is :" + balanceManagerProxy.getRegularBalance().toString() + " and his bonus balance is :" + balanceManagerProxy.getBonusBalance().toString());
            return;
        }

        sendNotification(GeneralNotifications.ITEM_DELIVERY_STARTED);

        DoorsAndCoverProxy doorsAndCoverProxy = (DoorsAndCoverProxy) facade.retrieveProxy(DoorsAndCoverProxy.NAME);
        doorsAndCoverProxy.openCoverForDelivery();

        MotorsProxy motorsProxy = (MotorsProxy) facade.retrieveProxy(MotorsProxy.NAME);
        motorsProxy.startItemDelivery(itemPosition);
    }
}
