package com.officer.policeofiicer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.officer.policeofiicer.domain.enumeration.PoliceOfficerStatus;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A PoliceOfficer.
 */
@Entity
@Table(name = "police_officer")
public class PoliceOfficer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PoliceOfficerStatus status;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(unique = true)
    private StolenBiker policeOfficer;

    @ManyToOne
    @JsonIgnoreProperties("ofiicers")
    private Police police;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PoliceOfficerStatus getStatus() {
        return status;
    }

    public PoliceOfficer status(PoliceOfficerStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(PoliceOfficerStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public PoliceOfficer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StolenBiker getPoliceOfficer() {
        return policeOfficer;
    }

    public PoliceOfficer policeOfficer(StolenBiker stolenBiker) {
        this.policeOfficer = stolenBiker;
        return this;
    }

    public void setPoliceOfficer(StolenBiker stolenBiker) {
        this.policeOfficer = stolenBiker;
    }

    public Police getPolice() {
        return police;
    }

    public PoliceOfficer police(Police police) {
        this.police = police;
        return this;
    }

    public void setPolice(Police police) {
        this.police = police;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PoliceOfficer)) {
            return false;
        }
        return id != null && id.equals(((PoliceOfficer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PoliceOfficer{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
