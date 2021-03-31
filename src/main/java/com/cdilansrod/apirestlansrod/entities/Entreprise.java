package com.cdilansrod.apirestlansrod.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Entreprise.
 */
@Entity
@Table(name = "entreprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "raison_sociale", length = 100, nullable = false)
    private String raisonSociale;

    @NotNull
    @Column(name = "siren", nullable = false)
    private String siren;

    @NotNull
    @Column(name = "siret", nullable = false)
    private String siret;

    @Size(min = 5, max = 100)
    @Column(name = "adresse", length = 100)
    private String adresse;

    @OneToMany(mappedBy = "entreprise")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Salarie> salaries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public Entreprise raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getSiren() {
        return siren;
    }

    public Entreprise siren(String siren) {
        this.siren = siren;
        return this;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public Entreprise siret(String siret) {
        this.siret = siret;
        return this;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getAdresse() {
        return adresse;
    }

    public Entreprise adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<Salarie> getSalaries() {
        return salaries;
    }

    public Entreprise salaries(Set<Salarie> salaries) {
        this.salaries = salaries;
        return this;
    }

    public Entreprise addSalarie(Salarie salarie) {
        this.salaries.add(salarie);
        salarie.setEntreprise(this);
        return this;
    }

    public Entreprise removeSalarie(Salarie salarie) {
        this.salaries.remove(salarie);
        salarie.setEntreprise(null);
        return this;
    }

    public void setSalaries(Set<Salarie> salaries) {
        this.salaries = salaries;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entreprise)) {
            return false;
        }
        return id != null && id.equals(((Entreprise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Entreprise{" +
            "id=" + getId() +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", siren='" + getSiren() + "'" +
            ", siret='" + getSiret() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
}
