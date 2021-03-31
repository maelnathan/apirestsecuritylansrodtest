package com.cdilansrod.apirestlansrod.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A Salarie.
 */
@Entity
@Table(name = "salarie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Salarie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(name = "prenom", length = 100, nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "nss", nullable = false)
    private String nss;

    @NotNull
    @Column(name = "date_embauche", nullable = false)
    private Instant dateEmbauche;

    @Column(name = "salaire", precision = 21, scale = 9)
    private Double salaire;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("salaries")
    private Entreprise entreprise;

    @ManyToOne(optional = false)
    @NotNull
    //@JsonIgnoreProperties(value = "salaries", allowSetters = true)
    @JsonIgnoreProperties("salaries")
    private Contrat contrat;



    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Salarie nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Salarie prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNss() {
        return nss;
    }

    public Salarie nss(String nss) {
        this.nss = nss;
        return this;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Instant getDateEmbauche() {
        return dateEmbauche;
    }

    public Salarie dateEmbauche(Instant dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this;
    }

    public void setDateEmbauche(Instant dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }


    public Double getSalaire() {
        return salaire;
    }

    public Salarie salaire(Double salaire) {
        this.salaire = salaire;
        return this;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Contrat getContrat() {
        return contrat;
    }
    public Salarie contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Salarie entreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salarie)) {
            return false;
        }
        return id != null && id.equals(((Salarie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Salarie{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nss='" + getNss() + "'" +
            ", dateEmbauche='" + getDateEmbauche() + "'" +
            ", contrat='" + getContrat() + "'" +
            ", salaire=" + getSalaire() +
            "}";
    }
}
