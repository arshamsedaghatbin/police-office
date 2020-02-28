package com.officer.policeofiicer.service.dto;

import com.officer.policeofiicer.domain.StolenStatus;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.police.domain.StolenBiker} entity.
 */
public class StolenBikerDTO implements Serializable {

    private Long id;

    private String licenseNumber;

    private String color;

    private String type;

    private String fullName;

    private ZonedDateTime date;

    private String description;


    private Long bikerId;

    private StolenStatus stolenStatus;


    public StolenStatus getStolenStatus() {
        return stolenStatus;
    }

    public void setStolenStatus(StolenStatus stolenStatus) {
        this.stolenStatus = stolenStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBikerId() {
        return bikerId;
    }

    public void setBikerId(Long bikerId) {
        this.bikerId = bikerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StolenBikerDTO stolenBikerDTO = (StolenBikerDTO) o;
        if (stolenBikerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stolenBikerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StolenBikerDTO{" +
            "id=" + getId() +
            ", licenseNumber='" + getLicenseNumber() + "'" +
            ", color='" + getColor() + "'" +
            ", type='" + getType() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", date='" + getDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", bikerId=" + getBikerId() +
            "}";
    }
}
