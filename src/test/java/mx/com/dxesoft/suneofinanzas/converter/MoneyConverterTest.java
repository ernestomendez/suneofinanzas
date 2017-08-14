package mx.com.dxesoft.suneofinanzas.converter;

import mx.com.dxesoft.util.BidxichiMoney;
import org.javamoney.moneta.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.money.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MoneyConverterTest {

    MoneyConverter moneyConverter = new MoneyConverter();

    @Test
    public void convertToDatabaseColumn() throws Exception {

        MonetaryContextBuilder monetaryContextBuilder = MonetaryContextBuilder.of();

        monetaryContextBuilder.setPrecision(5);

        MonetaryContext monetaryContext = monetaryContextBuilder.build();

        String mxn = "MXN";

        BidxichiMoney monetaryAmount = BidxichiMoney.withCurrency("123", mxn);
        BidxichiMoney monetaryAmount2 = BidxichiMoney.withCurrency("243.50", mxn);
        BidxichiMoney monetaryAmount3 = BidxichiMoney.withCurrency("123.5445566", mxn);
//        MonetaryAmount monetaryAmount4 = Money.of(10, mxn, monetaryContext).divide(3d);

        String column = moneyConverter.convertToDatabaseColumn(monetaryAmount);
        String column2 = moneyConverter.convertToDatabaseColumn(monetaryAmount2);
        String column3 = moneyConverter.convertToDatabaseColumn(monetaryAmount3);
//        String column4 = moneyConverter.convertToDatabaseColumn(monetaryAmount4);

        Assert.assertEquals("columna", "123.00|MXN", column);
        Assert.assertEquals("columna", "243.50|MXN", column2);
        Assert.assertEquals("columna", "123.54|MXN", column3);
//        Assert.assertEquals("columna", "3.33|MXN", column4);

    }

    @Test
    public void convertToEntityAttribute() throws Exception {

    }

}
