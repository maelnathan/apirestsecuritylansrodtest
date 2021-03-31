package com.cdilansrod.apirestlansrod.web.apirest;

import com.cdilansrod.apirestlansrod.dto.EntrepriseDTO;
import com.cdilansrod.apirestlansrod.dto.OullaResponse;
import com.cdilansrod.apirestlansrod.entities.Entreprise;
import com.cdilansrod.apirestlansrod.services.EntrepriseService;
import com.cdilansrod.apirestlansrod.tools.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Optional;


@RestController
@RequestMapping(Utility.URL_API_LANSROD)
public class EntrepriseResource {

    private final Logger log = LoggerFactory.getLogger(EntrepriseResource.class);
    private final EntrepriseService entrepriseService;

    public EntrepriseResource(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    /*====================================================
     * CREER UNE ENTREPRISE
     * ===============================================*/
    @PostMapping("/entreprises")
    @PreAuthorize("hasRole('ADMIN')")
    public OullaResponse<Entreprise> createEntreprise(@Valid @RequestBody EntrepriseDTO entrepriseDTO) throws URISyntaxException {
        log.debug("REST request to save Entreprise : {}", entrepriseDTO);
        OullaResponse<Entreprise> response = new OullaResponse<>();
        try {
            if (entrepriseDTO.getId() != null) {
                log.info("Une nouvelle entreprise ne peut pas déjà avoir ID");
            } else if (entrepriseDTO !=null){
                Entreprise result = entrepriseService.save(entrepriseDTO);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setStatus(true);
                response.setRecord(result);
            }
        } catch (Exception ex) {
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            System.out.println(ex.getMessage());
        }
        return response;
    }

    /*====================================================
     * MODIFIER UNE ENTREPRISE
     * ===============================================*/
    @PutMapping("/entreprises")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public OullaResponse<Optional<EntrepriseDTO>> updateEntreprise(@Valid @RequestBody EntrepriseDTO entrepriseDTO) throws URISyntaxException {
        log.debug("Requête pour modifier une entreprise : {}", entrepriseDTO);
        OullaResponse<Optional<EntrepriseDTO>> response = new OullaResponse<>();
        try {
            if (entrepriseDTO.getId() == null) {
                log.info("ID invalide il ne doit pas être vide");
            }else {
                Optional<EntrepriseDTO> updatedEntreprise = entrepriseService.updateEntreprise(entrepriseDTO);
                log.debug("le contenu de  updatedEntreprise: {}", updatedEntreprise);
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(updatedEntreprise);
            }
        } catch (Exception ex) {
            log.info("########## MODIFICATION ENTREPRISE : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            System.out.println(ex.getMessage());
        }
        return response;
    }

    /*====================================================
     * OBTENIR UNE ENTREPRISE
     * ===============================================*/
    @GetMapping("/entreprises/{id}")
    public  OullaResponse<Optional<EntrepriseDTO>> getEntreprise(@PathVariable Long id) {
        log.debug("Requête pour trouver une entreprise : {}", id);
        OullaResponse<Optional<EntrepriseDTO>> response = new OullaResponse<>();
        try {
            Optional<EntrepriseDTO> checkOneEntreprise =  entrepriseService.findOne(id).map(EntrepriseDTO::new);
            log.debug("le contenu de  checkOneEntreprise: {}", checkOneEntreprise);
            if (checkOneEntreprise != null) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(checkOneEntreprise);
            }
        } catch (Exception ex) {
            log.info("########## OBTENIR UNE ENTREPRISE : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            System.out.println(ex.getMessage());
        }
        return response;
    }

    /*====================================================
     * OBTENIR LES ENTREPRISES
     * ===============================================*/
    @GetMapping("/entreprises")
    public OullaResponse<Page<Entreprise>> getAllEntreprise(Pageable pageable) {
        log.debug("Requête pour obtenir les entreprises");
        OullaResponse<Page<Entreprise>> response = new OullaResponse<>();
        try {
            Page<Entreprise> page = entrepriseService.findAll(pageable);
            log.info("la liste des entreprises= "+page.getContent());
            if (!page.isEmpty()) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(page);
            }
        } catch (Exception ex) {
            log.info("########## LISTE DES ENTREPRISES : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            log.info("########## MESSAGE D'EXCEPTION : " + ex.getMessage());
        }
        return response;
    }

    /*====================================================
     * SUPPRIMER UNE ENTREPRISE
     * ===============================================*/
    @DeleteMapping("/entreprises/{id}")
    public OullaResponse<Optional<EntrepriseDTO>> deleteEntreprise(@PathVariable Long id) {
        log.debug("Requete pour supprimer une Entreprise : {}", id);
        OullaResponse<Optional<EntrepriseDTO>> response = new OullaResponse<>();
        try {
            Optional<EntrepriseDTO> checkOneEntreprise =  entrepriseService.findOne(id).map(EntrepriseDTO::new);
            log.info("########## Item entreprise à supprimer= "+checkOneEntreprise.toString());
            if (checkOneEntreprise != null) {
                entrepriseService.delete(id);
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(checkOneEntreprise);
            }
        } catch (Exception ex) {
            log.info("########## ITEM ENTREPRISE SUPPRIME: " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setStatus(false);
            response.setCode(Utility.CODE_FAILED);
            response.setMessage(Utility.MESSAGE_FAILED + " - " + ex.getMessage());
        }
        return response;
    }
}
