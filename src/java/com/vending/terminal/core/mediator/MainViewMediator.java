package com.vending.terminal.core.mediator;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.util.CommandArgumentParser;
import com.pi4j.wiringpi.Gpio;
import com.vending.terminal.core.mediator.components.MainViewComponent;
import com.vending.terminal.utils.AppLogger;
import org.puremvc.java.patterns.mediator.Mediator;

import java.util.concurrent.Callable;

public class MainViewMediator extends Mediator {

    public static String NAME = "MainViewMediator";
    private MainViewComponent view;
    private boolean counter;

    public MainViewMediator() {

        super(NAME, new MainViewComponent());
    }

    @Override
    public void onRegister() {
        view = (MainViewComponent) viewComponent;
//        view.createView();
        try {
            barierDetection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void barierDetection() {
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04,
                PinPullResistance.PULL_DOWN);

        myButton.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
            public Void call() throws Exception {
                System.out.println(" --> GPIO TRIGGER , state changed : " + myButton.getState());
                return null;
            }
        }));
        System.console().readLine();
    }

//    private void servo() throws Exception  {
//        AppLogger.getInstance().log("gettingFactory!");
//        GpioController gpio = GpioFactory.getInstance();
//        AppLogger.getInstance().log("Prepering Pin!");
//        Pin pin = CommandArgumentParser.getPin(
//                RaspiPin.class,    // pin provider class to obtain pin instance from
//                RaspiPin.GPIO_01  // default pin if no pin argument found
//                );             // argument array to search in
//
//        AppLogger.getInstance().log("setting state!");
//        GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(pin);
//        AppLogger.getInstance().log("setting mode!");
//        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
//        AppLogger.getInstance().log("setting range!");
//        Gpio.pwmSetRange(1920);
//        AppLogger.getInstance().log("setting clock!");
//        Gpio.pwmSetClock(200);
//
//        AppLogger.getInstance().log("Enter PWM:");
//        int value = 15;
//        while (value < 500){
//            value = Integer.parseInt(System.console().readLine());
//            int angle = Math.min(value + 50, 380) ;
//            pwm.setPwm(angle);
//            AppLogger.getInstance().log("current angle is:" + value);
//        }
//
//        gpio.shutdown();
//        AppLogger.getInstance().log("good bue!");
//    }
}
