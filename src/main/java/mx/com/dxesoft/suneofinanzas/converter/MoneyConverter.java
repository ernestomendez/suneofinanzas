package mx.com.dxesoft.suneofinanzas.converter;


import mx.com.dxesoft.util.BidxichiMoney;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MoneyConverter implements AttributeConverter<BidxichiMoney, String> {

    private static final String SEPARATOR = "|";

    @Override
    public String convertToDatabaseColumn(BidxichiMoney monetaryAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append(monetaryAmount.getValue()).append(SEPARATOR)
            .append(monetaryAmount.getCurrency());

        return sb.toString();
    }

    @Override
    public BidxichiMoney convertToEntityAttribute(String monetaryStr) {
        String[] monetary = monetaryStr.split("\\|");
        return BidxichiMoney.withCurrency(monetary[0], monetary[1]);
    }
}
