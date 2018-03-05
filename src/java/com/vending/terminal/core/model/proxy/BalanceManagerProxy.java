package com.vending.terminal.core.model.proxy;

import com.vending.terminal.core.config.GeneralNotifications;
import org.puremvc.java.patterns.proxy.Proxy;

import java.math.BigDecimal;

public class BalanceManagerProxy extends Proxy {

    private BigDecimal regularBalance;
    private BigDecimal bonusBalance;

    public static String NAME = "BalanceManagerProxy";

    public BalanceManagerProxy() {
        super(NAME);
    }

    @Override
    public void onRegister() {
        super.onRegister();

        regularBalance = new BigDecimal(0);
        bonusBalance = new BigDecimal(0);
    }

    public void addToBonusBalance(String value){
        bonusBalance = bonusBalance.add(new BigDecimal(value));
        updateBalance();
    }

    public void addToRegularBalance(String value){
        regularBalance = regularBalance.add(new BigDecimal(value));
        updateBalance();
    }

    public void removeFromTotalBalance(String value){
        if (bonusBalance.compareTo(BigDecimal.ZERO) == 0){

        }
        regularBalance = regularBalance.subtract(new BigDecimal(value));

    }

    public void isEnoughMoneyForItem(String value){
        BigDecimal totalAmount = new BigDecimal(value)
    }

    private void updateBalance(){
        sendNotification(GeneralNotifications.BONUS_BALANCE_UPDATED, bonusBalance.setScale(2, BigDecimal.ROUND_CEILING));
        sendNotification(GeneralNotifications.REGULAR_BALANCE_UPDATED, bonusBalance.setScale(2, BigDecimal.ROUND_CEILING));
    }
}