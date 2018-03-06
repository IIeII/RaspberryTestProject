import com.vending.terminal.core.model.proxy.BalanceManagerProxy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

public class MainTest {

    private BalanceManagerProxy balanceManager;

    @Before
    public void setup() {
        balanceManager = new BalanceManagerProxy();
        balanceManager.onRegister();
    }

    @Test
    public void test1() {
        assertEquals(0, BigDecimal.valueOf(5).compareTo(balanceManager.addToBonusBalance("5.00")));
        assertEquals(0, BigDecimal.valueOf(10).compareTo(balanceManager.addToRegularBalance("10.00")));
    }

    @Test
    public void test2() {

    }

    
}
