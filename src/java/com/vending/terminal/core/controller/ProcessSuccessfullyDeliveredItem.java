package com.vending.terminal.core.controller;

import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class ProcessSuccessfullyDeliveredItem extends SimpleCommand {
    @Override
    public void execute(INotification notification) {
        log("ProcessSuccessfullyDeliveredItem is executing");

        //log history
        //discard money
        //payout
        

    }
}
