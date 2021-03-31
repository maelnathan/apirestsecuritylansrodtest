package com.cdilansrod.apirestlansrod.services;


import com.cdilansrod.apirestlansrod.dto.SalarieDTO;
import com.cdilansrod.apirestlansrod.entities.Salarie;
import com.cdilansrod.apirestlansrod.repositories.SalarieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
@Transactional
public class SalarieService {

    private final Logger log = LoggerFactory.getLogger(SalarieService.class);
    private final SalarieRepository salarieRepository;

    public SalarieService(SalarieRepository salarieRepository) {
        this.salarieRepository = salarieRepository;
    }

    /*===========================================================================
     * ENREGISTRER UN SALARIE
     * =========================================================================*/
    public Salarie save(SalarieDTO salarieDTO) {
        log.debug("Requête pour enregistere un Salarié : {}", salarieDTO);
        Salarie newSalarie= new Salarie();
        newSalarie.setNom(salarieDTO.getNom());
        newSalarie.setPrenom(salarieDTO.getPrenom());
        newSalarie.setDateEmbauche(salarieDTO.getDateEmbauche());

        if(salarieDTO.getSalaire().doubleValue() >0){
        newSalarie.setSalaire(salarieDTO.getSalaire());
        }
        newSalarie.setNss(salarieDTO.getNss());
        if(salarieDTO.getContrat().getId() !=null) {
            newSalarie.setContrat(salarieDTO.getContrat());
        }
        if(salarieDTO.getEntreprise().getId() !=null) {
            newSalarie.setEntreprise(salarieDTO.getEntreprise());
        }
        salarieRepository.save(newSalarie);
        return newSalarie;
    }

    /*===========================================================================
     * MODIFIER UN SALARIE
     * =========================================================================*/
    public Optional<SalarieDTO> updateSalarie(SalarieDTO salarieDTO) {
        return Optional.of(salarieRepository
                .findById(salarieDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(salarie -> {
                    salarie.setSalaire(salarieDTO.getSalaire());
                    salarie.setNss(salarieDTO.getNss());
                    salarie.setPrenom(salarieDTO.getPrenom());
                    salarie.setNom(salarieDTO.getNom());
                    salarie.setContrat(salarieDTO.getContrat());
                    salarie.setEntreprise(salarieDTO.getEntreprise());
                    log.debug("Les informations modifiées du salarié: {}", salarie);
                    return salarie;
                })
                .map(SalarieDTO::new);
    }

    /*===========================================================================
     * OBTENIR UN SALARIE
     * =========================================================================*/
    @Transactional(readOnly = true)
    public Optional<Salarie> findOne(Long id) {
        log.debug("Request to get Salarie : {}", id);
        return salarieRepository.findById(id);
    }


    /*===========================================================================
     * OBTENIR LES SALARIES
     * =========================================================================*/
    @Transactional(readOnly = true)
    public Page<Salarie> findAll(Pageable pageable) {
        log.debug("Request to get all Salaries");
        return salarieRepository.findAll(pageable);
    }




    /*===========================================================================
     * SUPPRIMER UN SALARIE
     * =========================================================================*/
    public void delete(Long id) {
        log.debug("Request to delete Salarie : {}", id);
        salarieRepository.deleteById(id);
    }
}
