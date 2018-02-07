package com.vending.terminal.utils;

public class AppLogger {

    private static AppLogger instance;

    public static AppLogger getInstance(){
        if (instance == null)
        {
            instance = new AppLogger();
        }
        return (AppLogger)instance;
    }

    public void log(String message){
        System.out.println(message);
    }
    public void error(String message){
        System.err.println(message);
    }
}
