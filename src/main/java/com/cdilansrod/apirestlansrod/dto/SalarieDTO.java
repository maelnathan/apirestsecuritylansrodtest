package com.cdilansrod.apirestlansrod.dto;

import com.cdilansrod.apirestlansrod.entities.Contrat;
import com.cdilansrod.apirestlansrod.entities.Entreprise;
import com.cdilansrod.apirestlansrod.entities.Salarie;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

public class SalarieDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String nom;

    @NotNull
    @Size(min = 5, max = 100)
    private String prenom;

    @NotNull
    private String nss;

    @NotNull
    private Instant dateEmbauche;

    private Double salaire;

    private Contrat contrat;

    private Entreprise entreprise;

    public SalarieDTO() {
        // Empty constructor needed for Jackson.
    }

    public SalarieDTO(Salarie salarie) {
        this.id = salarie.getId();
        this.dateEmbauche = salarie.getDateEmbauche();
        this.nom = salarie.getNom();
        this.prenom = salarie.getPrenom();
        this.salaire = salarie.getSalaire();
        this.contrat = salarie.getContrat();
        this.entreprise = salarie.getEntreprise();
        this.nss = salarie.getNss();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Instant getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Instant dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Contrat getContrat() { return contrat; }
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalarieDTO)) {
            return false;
        }

        return id != null && id.equals(((SalarieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SalarieDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nss='" + nss + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                ", salaire=" + salaire +
                ", contrat=" + contrat +
                ", entreprise=" + entreprise +
                '}';
    }
}
