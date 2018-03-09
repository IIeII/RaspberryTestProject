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
        resetBalance();
    }

    public void resetBalance(){
        log("Resetting current balance to 0.");
        regularBalance = new BigDecimal(0);
        bonusBalance = new BigDecimal(0);
        notifyThatBalanceIsUpdated();
    }

    public BigDecimal addToBonusBalance(String value){
        bonusBalance = bonusBalance.add(new BigDecimal(value));
        log("Adding money: '" + value +"' to bonus balance. Current bonus balance is '" + bonusBalance.toString() + "'");
        notifyThatBalanceIsUpdated();

        return bonusBalance;
    }

    public BigDecimal addToRegularBalance(String value){
        regularBalance = regularBalance.add(new BigDecimal(value));
        log("Adding money: '" + value +"' to regular balance. Current regular balance is '" + regularBalance.toString() + "'");
        notifyThatBalanceIsUpdated();

        return regularBalance;
    }

    public BigDecimal chargeFromTotalBalance(String value){

        log("Charging money from balance in value: '" + value + "'");

        if (!isEnoughMoneyForItem(value)){
            err("BalanceManagerProxy.removeFromTotalBalance() error! Price to remove can't be bigger than total amount of money!");
            return null;
        }

        BigDecimal price = new BigDecimal(value);
        if (bonusBalance.compareTo(BigDecimal.ZERO) > 0){
            if (bonusBalance.compareTo(price) >= 0){
                err("Possible problem. Bonus balance is big enough to buy item! Please check!");
                bonusBalance = new BigDecimal(0);
                notifyThatBalanceIsUpdated();
                return regularBalance;
            }
        }

        regularBalance = (regularBalance.add(bonusBalance)).subtract(price);
        notifyThatBalanceIsUpdated();
        return regularBalance;
    }

    public BigDecimal getRegularBalance() {
        return regularBalance;
    }

    public BigDecimal getBonusBalance() {
        return bonusBalance;
    }

    public Boolean isEnoughMoneyForItem(String price){
        BigDecimal totalBalance = regularBalance.add(bonusBalance);
        int comparisionResult = new BigDecimal(price).compareTo(totalBalance);
        return comparisionResult <= 0;
    }

    private void notifyThatBalanceIsUpdated(){
        sendNotification(GeneralNotifications.BONUS_BALANCE_UPDATED, bonusBalance.setScale(2, BigDecimal.ROUND_CEILING));
        sendNotification(GeneralNotifications.REGULAR_BALANCE_UPDATED, bonusBalance.setScale(2, BigDecimal.ROUND_CEILING));
    }
}
