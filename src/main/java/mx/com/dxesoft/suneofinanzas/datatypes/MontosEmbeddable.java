package mx.com.dxesoft.suneofinanzas.datatypes;

import lombok.Data;
import mx.com.dxesoft.suneofinanzas.converter.MoneyConverter;
import mx.com.dxesoft.util.BidxichiMoney;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.datatypes . MontosEmbeddable
 * Created by ernesto on 17/08/17.
 */
@Embeddable
public class MontosEmbeddable {

    @Column
    @NotNull
    @Convert(converter = MoneyConverter.class)
    private BidxichiMoney amount;

    @Column
    @NotNull
    @Convert(converter = MoneyConverter.class)
    private BidxichiMoney taxes;

    public BidxichiMoney getAmount() {
        return amount;
    }

    public void setAmount(BidxichiMoney amount) {
        this.amount = amount;
    }

    public BidxichiMoney getTaxes() {
        return taxes;
    }

    public void setTaxes(BidxichiMoney taxes) {
        this.taxes = taxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MontosEmbeddable)) return false;
        MontosEmbeddable that = (MontosEmbeddable) o;
        return Objects.equals(getAmount(), that.getAmount()) &&
            Objects.equals(getTaxes(), that.getTaxes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getTaxes());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("amount", amount)
            .append("taxes", taxes)
            .toString();
    }
}
