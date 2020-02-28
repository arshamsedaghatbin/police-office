package com.officer.policeofiicer.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Biker.
 */
@Entity
@Table(name = "biker")
public class Biker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "biker")
    private Set<StolenBiker> stolenBikes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Biker name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StolenBiker> getStolenBikes() {
        return stolenBikes;
    }

    public Biker stolenBikes(Set<StolenBiker> stolenBikers) {
        this.stolenBikes = stolenBikers;
        return this;
    }

    public Biker addStolenBike(StolenBiker stolenBiker) {
        this.stolenBikes.add(stolenBiker);
        stolenBiker.setBiker(this);
        return this;
    }

    public Biker removeStolenBike(StolenBiker stolenBiker) {
        this.stolenBikes.remove(stolenBiker);
        stolenBiker.setBiker(null);
        return this;
    }

    public void setStolenBikes(Set<StolenBiker> stolenBikers) {
        this.stolenBikes = stolenBikers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Biker)) {
            return false;
        }
        return id != null && id.equals(((Biker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Biker{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
