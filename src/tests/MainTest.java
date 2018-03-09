import com.vending.terminal.core.model.proxy.BalanceManagerProxy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class MainTest {

    private BalanceManagerProxy balanceManager;

    @Before
    public void setup() {
        balanceManager = new BalanceManagerProxy();
        balanceManager.onRegister();
    }

    @Test
    public void addMoneyToBalance() {
        assertEquals(0, BigDecimal.valueOf(5).compareTo(balanceManager.addToBonusBalance("5.00")));
        assertEquals(0, BigDecimal.valueOf(10).compareTo(balanceManager.addToRegularBalance("10.00")));
    }

    @Test
    public void testIsEnoughMoneyForItem() {
        addMoneyToBalance();
        assertEquals(Boolean.TRUE, balanceManager.isEnoughMoneyForItem("10.00"));
        assertEquals(Boolean.TRUE, balanceManager.isEnoughMoneyForItem("10"));
        assertEquals(Boolean.TRUE, balanceManager.isEnoughMoneyForItem("10.0"));
        assertEquals(Boolean.TRUE, balanceManager.isEnoughMoneyForItem("14.99"));
        assertEquals(Boolean.TRUE, balanceManager.isEnoughMoneyForItem("15.00"));
        assertEquals(Boolean.FALSE, balanceManager.isEnoughMoneyForItem("15.01"));
        assertEquals(Boolean.FALSE, balanceManager.isEnoughMoneyForItem("15.10"));
        assertEquals(Boolean.FALSE, balanceManager.isEnoughMoneyForItem("16.00"));
    }
    @Test
    public void testOfCharge1() {
        addMoneyToBalance();
        assertEquals(0, BigDecimal.valueOf(5).compareTo(balanceManager.chargeFromTotalBalance("10.00")));
    }
    @Test
    public void testOfCharge2() {
        addMoneyToBalance();
        assertEquals(0, BigDecimal.valueOf(0).compareTo(balanceManager.chargeFromTotalBalance("15")));
    }
    @Test
    public void testOfCharge3() {
        addMoneyToBalance();
        assertEquals(0, BigDecimal.valueOf(10).compareTo(balanceManager.chargeFromTotalBalance("3")));
    }
    @Test
    public void testOfCharge4() {
        addMoneyToBalance();
        assertNull(balanceManager.chargeFromTotalBalance("20"));
    }



}
