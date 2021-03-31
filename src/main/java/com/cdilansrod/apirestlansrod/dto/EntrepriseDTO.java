package com.cdilansrod.apirestlansrod.dto;

import com.cdilansrod.apirestlansrod.entities.Entreprise;

import javax.validation.constraints.*;
import java.io.Serializable;

public class EntrepriseDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String raisonSociale;

    @NotNull
    private String siren;

    @NotNull
    private String siret;

    @Size(min = 5, max = 100)
    private String adresse;

    public EntrepriseDTO() {
        // Empty constructor needed for Jackson.
    }

    public EntrepriseDTO(Entreprise entreprise) {
        this.id = entreprise.getId();
        this.adresse = entreprise.getAdresse();
        this.raisonSociale = entreprise.getRaisonSociale();
        this.siren = entreprise.getSiren();
        this.siret = entreprise.getSiret();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntrepriseDTO)) {
            return false;
        }

        return id != null && id.equals(((EntrepriseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EntrepriseDTO{" +
            "id=" + getId() +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", siren='" + getSiren() + "'" +
            ", siret='" + getSiret() + "'" +
            ", adresse='" + getAdresse() + "'" +
            "}";
    }
}
