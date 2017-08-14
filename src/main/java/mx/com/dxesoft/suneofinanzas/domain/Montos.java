package mx.com.dxesoft.suneofinanzas.domain;

import mx.com.dxesoft.suneofinanzas.converter.MoneyConverter;
import mx.com.dxesoft.util.BidxichiMoney;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Montos.
 */
@Entity
@Table(name = "montos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Montos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "value", nullable = false)
    @Convert(converter = MoneyConverter.class)
    private BidxichiMoney amount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "ciclo")
    private String ciclo;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "tax")
    @Convert(converter = MoneyConverter.class)
    private BidxichiMoney tax;

    @ManyToOne(optional = false)
    @NotNull
    private ConceptosDePago concepto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BidxichiMoney getAmount() {
        return amount;
    }

    public Montos amount(BidxichiMoney amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BidxichiMoney amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Montos startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Montos endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCiclo() {
        return ciclo;
    }

    public Montos ciclo(String ciclo) {
        this.ciclo = ciclo;
        return this;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Boolean isActive() {
        return active;
    }

    public Montos active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BidxichiMoney getTax() {
        return tax;
    }

    public Montos tax(BidxichiMoney tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(BidxichiMoney tax) {
        this.tax = tax;
    }

    public ConceptosDePago getConcepto() {
        return concepto;
    }

    public Montos concepto(ConceptosDePago conceptosDePago) {
        this.concepto = conceptosDePago;
        return this;
    }

    public void setConcepto(ConceptosDePago conceptosDePago) {
        this.concepto = conceptosDePago;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Montos)) return false;
        Montos montos = (Montos) o;
        return Objects.equals(getId(), montos.getId()) &&
            Objects.equals(getAmount(), montos.getAmount()) &&
            Objects.equals(getStartDate(), montos.getStartDate()) &&
            Objects.equals(getEndDate(), montos.getEndDate()) &&
            Objects.equals(getCiclo(), montos.getCiclo()) &&
            Objects.equals(active, montos.active) &&
            Objects.equals(getTax(), montos.getTax()) &&
            Objects.equals(getConcepto(), montos.getConcepto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount(), getStartDate(), getEndDate(), getCiclo(), active, getTax(), getConcepto());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("amount", amount)
            .append("startDate", startDate)
            .append("endDate", endDate)
            .append("ciclo", ciclo)
            .append("active", active)
            .append("tax", tax)
            .append("concepto", concepto)
            .toString();
    }
}
