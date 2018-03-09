package com.vending.terminal.core.mediator;

import com.vending.terminal.core.mediator.components.MainStateComponent;
import org.puremvc.java.patterns.mediator.Mediator;

public class StateMediator extends Mediator {

    public static String NAME = "StateMediator";

    public StateMediator() {

        super(NAME, new MainStateComponent());
    }


}
