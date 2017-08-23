package mx.com.dxesoft.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Class to represent the Monetary transactions.
 *
 * <I>Note:La palabra <B>Bidxichi</B> deriva del <U>Zapoteco</U> y significa Dinero</I>
 */
public final class BidxichiMoney implements Serializable {

    private static final long serialVersionUID = 8255033999309392048L;
    private static final int DECIMALS = 2;
    private static final String DEFAULT_CURRENCY= "MXN";

    private BigDecimal value;

    private String currency;

    public BidxichiMoney() {
        value = getBigDecimal(0);
        currency = DEFAULT_CURRENCY;
    }

    private BidxichiMoney(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public static BidxichiMoney withCurrency(String value, String currency) {
        BigDecimal val = getBigDecimal(value);

        return new BidxichiMoney(val, currency);
    }

    public static BidxichiMoney withoutCurrency(String value) {
        BigDecimal val = getBigDecimal(value);

        return new BidxichiMoney(val, DEFAULT_CURRENCY);
    }

    public static BidxichiMoney withCurrency(double value, String currency) {
        BigDecimal val = getBigDecimal(value);

        return new BidxichiMoney(val, currency);
    }

    public static BidxichiMoney withoutCurrency(double value) {
        BigDecimal val = getBigDecimal(value);

        return new BidxichiMoney(val, DEFAULT_CURRENCY);
    }

    private static BigDecimal getBigDecimal(String value) {
        return new BigDecimal(value).setScale(DECIMALS, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal getBigDecimal(double value) {
        return new BigDecimal(value).setScale(DECIMALS, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    /**
     * Adds a bixdichiMoney
     *
     * @param bidxichiMoney bidxichiMoney to be added.
     */
    public void add(BidxichiMoney bidxichiMoney) {
        this.value = this.getValue().add(bidxichiMoney.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidxichiMoney)) return false;
        BidxichiMoney that = (BidxichiMoney) o;
        return Objects.equals(getValue(), that.getValue()) &&
            Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getCurrency());
    }

    @Override
    public String toString() {
        return value + " | " + currency;
    }
}
