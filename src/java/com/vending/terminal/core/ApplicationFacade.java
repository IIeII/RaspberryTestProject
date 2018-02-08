package com.vending.terminal.core;

import com.vending.terminal.core.config.GeneralNotifications;
import org.puremvc.java.interfaces.ICommand;
import org.puremvc.java.patterns.facade.Facade;

public class ApplicationFacade extends Facade {

    public static ApplicationFacade getInstance(){
        if (instance == null)
        {
            instance = new ApplicationFacade();
        }
        return (ApplicationFacade)instance;
    }

    public void startup(ICommand command)
    {
        registerCommand(GeneralNotifications.STARTUP, command);
        sendNotification(GeneralNotifications.STARTUP);
    }
}
