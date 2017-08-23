package mx.com.dxesoft.suneofinanzas.datatypes;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Alumno implements Serializable {

    private static final long serialVersionUID = -5288562929870918197L;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String curp;

    @Column
    @NotNull
    private String matricula;

    public Alumno() {
        this.name = null;
        this.curp = null;
        this.matricula = null;
    }

    public Alumno(String name, String curp, String matricula) {
        this.name = name;
        this.curp = curp;
        this.matricula = matricula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(getName(), alumno.getName()) &&
            Objects.equals(getCurp(), alumno.getCurp()) &&
            Objects.equals(getMatricula(), alumno.getMatricula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCurp(), getMatricula());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name)
            .append("curp", curp)
            .append("matricula", matricula)
            .toString();
    }
}
