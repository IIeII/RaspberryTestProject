package com.vending.terminal.core.mediator;

import com.vending.terminal.core.config.GeneralNotifications;
import com.vending.terminal.core.mediator.components.MainStateComponent;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.mediator.Mediator;

public class StateMediator extends Mediator {

    public static String NAME = "StateMediator";

    public StateMediator() {

        super(NAME, new MainStateComponent());
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[]{

        };
    }

    @Override
    public void handleNotification(INotification notification) {
        super.handleNotification(notification);

//        switch (notification.getName()){
//
//        }
    }


    private void onDecisionItemWasDelivered(){
        sendNotification(GeneralNotifications.ITEM_SUCCESSFULLY_DELIVERED);
    }
}
