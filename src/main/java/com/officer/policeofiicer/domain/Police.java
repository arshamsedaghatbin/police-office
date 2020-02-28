package com.officer.policeofiicer.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Police.
 */
@Entity
@Table(name = "police")
public class Police implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "police")
    private Set<PoliceOfficer> ofiicers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public Police address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<PoliceOfficer> getOfiicers() {
        return ofiicers;
    }

    public Police ofiicers(Set<PoliceOfficer> policeOfficers) {
        this.ofiicers = policeOfficers;
        return this;
    }

    public Police addOfiicer(PoliceOfficer policeOfficer) {
        this.ofiicers.add(policeOfficer);
        policeOfficer.setPolice(this);
        return this;
    }

    public Police removeOfiicer(PoliceOfficer policeOfficer) {
        this.ofiicers.remove(policeOfficer);
        policeOfficer.setPolice(null);
        return this;
    }

    public void setOfiicers(Set<PoliceOfficer> policeOfficers) {
        this.ofiicers = policeOfficers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Police)) {
            return false;
        }
        return id != null && id.equals(((Police) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Police{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
