package com.vending.terminal.core.controller;

import com.vending.terminal.utils.AppLogger;
import org.puremvc.java.patterns.command.SimpleCommand;

public class BaseSimpleCommand extends SimpleCommand {

    protected void log(String message){
        AppLogger.getInstance().log(message);
    }
    protected void err(String message){
        AppLogger.getInstance().error(message);
    }
}
