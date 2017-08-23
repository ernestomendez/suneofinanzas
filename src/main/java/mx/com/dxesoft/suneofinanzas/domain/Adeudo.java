package mx.com.dxesoft.suneofinanzas.domain;

import lombok.Data;
import lombok.ToString;
import mx.com.dxesoft.suneofinanzas.datatypes.Alumno;
import mx.com.dxesoft.suneofinanzas.datatypes.MontosEmbeddable;
import mx.com.dxesoft.suneofinanzas.domain.enumeration.AdeudosStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.domain . Adeudo
 * Created by ernesto on 18/08/17.
 */
@Entity
@Table(name = "adeudos")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Adeudo implements Serializable {

    private static final long serialVersionUID = 4018074700793034268L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "concepto_id")
    @NotNull
    private ConceptosDePago conceptosDePago;

    @Column
    @NotNull
    private String conceptoName;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private AdeudosStatus status;

    @Embedded
    private MontosEmbeddable montosEmbeddable;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "alumno_name")),
        @AttributeOverride(name = "curp", column = @Column(name = "alumno_curp")),
        @AttributeOverride(name = "matricula", column = @Column(name = "alumno_matricula"))
    })
    private Alumno alumno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConceptosDePago getConceptosDePago() {
        return conceptosDePago;
    }

    public void setConceptosDePago(ConceptosDePago conceptosDePago) {
        this.conceptosDePago = conceptosDePago;
    }

    public String getConceptoName() {
        return conceptoName;
    }

    public void setConceptoName(String conceptoName) {
        this.conceptoName = conceptoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdeudosStatus getStatus() {
        return status;
    }

    public void setStatus(AdeudosStatus status) {
        this.status = status;
    }

    public MontosEmbeddable getMontosEmbeddable() {
        return montosEmbeddable;
    }

    public void setMontosEmbeddable(MontosEmbeddable montosEmbeddable) {
        this.montosEmbeddable = montosEmbeddable;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adeudo)) return false;
        Adeudo adeudo = (Adeudo) o;
        return Objects.equals(getId(), adeudo.getId()) &&
            Objects.equals(getConceptosDePago(), adeudo.getConceptosDePago()) &&
            Objects.equals(getConceptoName(), adeudo.getConceptoName()) &&
            Objects.equals(getDescription(), adeudo.getDescription()) &&
            getStatus() == adeudo.getStatus() &&
            Objects.equals(getMontosEmbeddable(), adeudo.getMontosEmbeddable()) &&
            Objects.equals(getAlumno(), adeudo.getAlumno());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConceptosDePago(), getConceptoName(), getDescription(), getStatus(), getMontosEmbeddable(), getAlumno());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("conceptosDePago", conceptosDePago)
            .append("conceptoName", conceptoName)
            .append("description", description)
            .append("status", status)
            .append("montosEmbeddable", montosEmbeddable)
            .append("alumno", alumno)
            .toString();
    }
}
