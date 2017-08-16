package mx.com.dxesoft.suneofinanzas.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A ConceptosDePago.
 */
@Entity
@Table(name = "conceptos_de_pago")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@EqualsAndHashCode
@ToString
public class ConceptosDePago implements Serializable {

    private static final long serialVersionUID = 5703548537787657371L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 300)
    @Column(name = "name", length = 300, nullable = false)
    private String name;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "disabled_date")
    private LocalDate disabledDate;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "taxpercentaje", nullable = false)
    private Integer taxpercentaje;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "linea_captura_required")
    private Boolean lineaCapturaRequired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ConceptosDePago name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public ConceptosDePago description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public ConceptosDePago creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDisabledDate() {
        return disabledDate;
    }

    public ConceptosDePago disabledDate(LocalDate disabledDate) {
        this.disabledDate = disabledDate;
        return this;
    }

    public void setDisabledDate(LocalDate disabledDate) {
        this.disabledDate = disabledDate;
    }

    public Integer getTaxpercentaje() {
        return taxpercentaje;
    }

    public ConceptosDePago taxpercentaje(Integer taxpercentaje) {
        this.taxpercentaje = taxpercentaje;
        return this;
    }

    public void setTaxpercentaje(Integer taxpercentaje) {
        this.taxpercentaje = taxpercentaje;
    }

    public Boolean isActive() {
        return active;
    }

    public ConceptosDePago active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isGeneraAdeudo() {
        return lineaCapturaRequired;
    }

    public void setLineaCapturaRequired(Boolean lineaCapturaRequired) {
        this.lineaCapturaRequired = lineaCapturaRequired;
    }

    public ConceptosDePago generaAdeudo(Boolean generaAdeudo) {
        this.lineaCapturaRequired = generaAdeudo;
        return this;
    }
}
