package mx.com.dxesoft.suneofinanzas.service.dto;

import lombok.Getter;
import mx.com.dxesoft.suneofinanzas.datatypes.Alumno;
import mx.com.dxesoft.suneofinanzas.domain.Adeudo;
import mx.com.dxesoft.util.BidxichiMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.service.dto . AdeudoDto
 * Created by ernesto on 20/08/17.
 */
@Getter
public final class AdeudoDto implements Serializable {

    private static final long serialVersionUID = 8296472083651519518L;

    private final Alumno alumno;

    private final List<Adeudo> adeudos;

    private final BidxichiMoney saldo;

    public AdeudoDto(Alumno alumno, List<Adeudo> adeudos, BidxichiMoney saldo) {
        this.alumno = alumno;
        this.adeudos = adeudos;
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdeudoDto)) return false;
        AdeudoDto adeudoDto = (AdeudoDto) o;
        return Objects.equals(alumno, adeudoDto.alumno) &&
            Objects.equals(adeudos, adeudoDto.adeudos) &&
            Objects.equals(saldo, adeudoDto.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alumno, adeudos, saldo);
    }
}
