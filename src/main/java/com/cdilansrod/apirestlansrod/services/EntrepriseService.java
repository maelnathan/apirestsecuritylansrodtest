package com.cdilansrod.apirestlansrod.services;

import com.cdilansrod.apirestlansrod.dto.EntrepriseDTO;
import com.cdilansrod.apirestlansrod.entities.Entreprise;
import com.cdilansrod.apirestlansrod.repositories.EntrepriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
@Transactional
public class EntrepriseService {

    private final Logger log = LoggerFactory.getLogger(EntrepriseService.class);
    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    /*===========================================================================
     * ENREGISTRER UNE ENTREPRISE
     * =========================================================================*/
    public Entreprise save(EntrepriseDTO entrepriseDTO) {
        log.debug("Requête pour enregistrer une Entreprise : {}", entrepriseDTO);
        Entreprise newEntreprise = new Entreprise();
        newEntreprise.setSiren(entrepriseDTO.getSiren());
        newEntreprise.setSiret(entrepriseDTO.getSiret());
        newEntreprise.setAdresse(entrepriseDTO.getAdresse());
        newEntreprise.setRaisonSociale(entrepriseDTO.getRaisonSociale());
        entrepriseRepository.save(newEntreprise);
        return newEntreprise;
    }

    /*===========================================================================
     * MODIFIER UNE ENTREPRISE
     * =========================================================================*/
    public Optional<EntrepriseDTO> updateEntreprise(EntrepriseDTO entrepriseDTO) {
        return Optional.of(entrepriseRepository
                .findById(entrepriseDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(entreprise -> {
                    entreprise.setRaisonSociale(entrepriseDTO.getRaisonSociale());
                    entreprise.setAdresse(entrepriseDTO.getAdresse());
                    entreprise.setSiret(entrepriseDTO.getSiret());
                    entreprise.setSiren(entrepriseDTO.getSiren());
                    log.debug("Les informations modifiées de l'entreprise: {}", entreprise);
                    return entreprise;
                })
                .map(EntrepriseDTO::new);
    }

    /*===========================================================================
     * OBTENIR UNE ENTREPRISE
     * =========================================================================*/
    @Transactional(readOnly = true)
    public Optional<Entreprise> findOne(Long id) {
        log.debug("Request to get Entreprise : {}", id);
        return entrepriseRepository.findById(id);
    }

    /*===========================================================================
     * OBTENIR LES ENTREPRISES
     * =========================================================================*/
    @Transactional(readOnly = true)
    public Page<Entreprise> findAll(Pageable pageable) {
        log.debug("Request to get all Entreprises");
        return entrepriseRepository.findAll(pageable);
    }



    /*===========================================================================
     * SUPPRIMER UNE ENTREPRISE
     * =========================================================================*/
    public void delete(Long id) {
        log.debug("Request to delete Entreprise : {}", id);
        entrepriseRepository.deleteById(id);
    }
}
