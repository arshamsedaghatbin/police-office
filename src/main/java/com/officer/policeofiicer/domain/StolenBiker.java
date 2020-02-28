package com.officer.policeofiicer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A StolenBiker.
 */
@Entity
@Table(name = "stolen_biker")
public class StolenBiker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "color")
    private String color;

    @Column(name = "type")
    private String type;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("stolenBikes")
    private Biker biker;

    @Enumerated(EnumType.STRING)
    private StolenStatus stolenStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public StolenBiker licenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getColor() {
        return color;
    }

    public StolenBiker color(String color) {
        this.color = color;
        return this;
    }

    public StolenStatus getStolenStatus() {
        return stolenStatus;
    }

    public void setStolenStatus(StolenStatus stolenStatus) {
        this.stolenStatus = stolenStatus;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public StolenBiker type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public StolenBiker fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public StolenBiker date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public StolenBiker description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Biker getBiker() {
        return biker;
    }

    public StolenBiker biker(Biker biker) {
        this.biker = biker;
        return this;
    }

    public void setBiker(Biker biker) {
        this.biker = biker;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StolenBiker)) {
            return false;
        }
        return id != null && id.equals(((StolenBiker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "StolenBiker{" +
            "id=" + getId() +
            ", licenseNumber='" + getLicenseNumber() + "'" +
            ", color='" + getColor() + "'" +
            ", type='" + getType() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
