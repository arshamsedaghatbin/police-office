package com.officer.policeofiicer.service.dto;

import com.officer.policeofiicer.domain.PoliceOfficer;
import com.officer.policeofiicer.domain.enumeration.PoliceOfficerStatus;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link PoliceOfficer} entity.
 */
public class PoliceOfficerDTO implements Serializable {

    private Long id;

    private PoliceOfficerStatus status;

    private String name;


    private Long policeOfficerId;

    private Long policeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PoliceOfficerStatus getStatus() {
        return status;
    }

    public void setStatus(PoliceOfficerStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoliceOfficerId() {
        return policeOfficerId;
    }

    public void setPoliceOfficerId(Long stolenBikerId) {
        this.policeOfficerId = stolenBikerId;
    }

    public Long getPoliceId() {
        return policeId;
    }

    public void setPoliceId(Long policeId) {
        this.policeId = policeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PoliceOfficerDTO policeOfficerDTO = (PoliceOfficerDTO) o;
        if (policeOfficerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), policeOfficerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PoliceOfficerDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", name='" + getName() + "'" +
            ", policeOfficerId=" + getPoliceOfficerId() +
            ", policeId=" + getPoliceId() +
            "}";
    }
}
