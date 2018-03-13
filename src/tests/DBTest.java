import com.vending.terminal.core.model.proxy.DataBaseProxy;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;


public class DBTest {

    private DataBaseProxy dataBaseProxy;

    @Before
    public void setup() {
        dataBaseProxy = new DataBaseProxy();
        dataBaseProxy.onRegister();
    }

    @Test
    public void addMoneyToBalance() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/

//        dataBaseProxy.saveTransactionHistory(dateFormat.format(date), 1, "a2", "10.00", "20.00", "0.00");
        dataBaseProxy.updateItemsCount(1,"a3");
    }
}
