package com.vending.terminal.core.controller;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.vending.terminal.core.mediator.MainViewMediator;
import org.puremvc.java.interfaces.INotification;
import org.puremvc.java.patterns.command.SimpleCommand;

public class StartupCommand extends SimpleCommand {
    @Override
    public void execute(INotification notification) {

        log("StartupCommand is executing");

        //commands:

        //mediators:
        facade.registerMediator(new MainViewMediator());

        //proxy




    }
}
