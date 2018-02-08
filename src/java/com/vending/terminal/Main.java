package com.vending.terminal;

import com.vending.terminal.core.ApplicationFacade;
import com.vending.terminal.core.controller.StartupCommand;
import com.vending.terminal.utils.AppLogger;

public class Main {

    public static void main(String[] args) {
        AppLogger.getInstance().log("Starting system");
        ApplicationFacade.getInstance().startup(new StartupCommand());
        final GpioController gpio = GpioFactory.getInstance();
    }
}
