package com.cdilansrod.apirestlansrod.dto;

import com.cdilansrod.apirestlansrod.entities.Contrat;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ContratDTO implements Serializable {

    private Long id;

    @NotNull
    private String nom;

    private String description;

    public ContratDTO() {
        // Empty constructor needed for Jackson.
    }

    public ContratDTO(Contrat contrat) {
        this.id = contrat.getId();
        this.nom = contrat.getNom();
        this.description = contrat.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContratDTO)) {
            return false;
        }

        return id != null && id.equals(((ContratDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "ContratDTO{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
