package mx.com.dxesoft.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BidxichiMoneyTest {
    @Test
    public void withCurrency() throws Exception {

        BidxichiMoney money = BidxichiMoney.withCurrency("123.23", "MXN");
        BidxichiMoney money2 = BidxichiMoney.withCurrency("123.00", "MXN");
        BidxichiMoney money3 = BidxichiMoney.withCurrency("123.569", "MXN");

        Assert.assertEquals("Cadenas iguales", "123.23 | MXN", money.toString());
        Assert.assertEquals("Cadenas iguales", "123.00 | MXN", money2.toString());
        Assert.assertEquals("Cadenas iguales", "123.57 | MXN", money3.toString());

    }

    @Test
    public void withoutCurrency() throws Exception {

        BidxichiMoney money = BidxichiMoney.withoutCurrency("123.23");
        BidxichiMoney money2 = BidxichiMoney.withoutCurrency("123.00");
        BidxichiMoney money3 = BidxichiMoney.withoutCurrency("123.569");

        Assert.assertEquals("Cadenas iguales", "123.23 | MXN", money.toString());
        Assert.assertEquals("Cadenas iguales", "123.00 | MXN", money2.toString());
        Assert.assertEquals("Cadenas iguales", "123.57 | MXN", money3.toString());
    }

}
