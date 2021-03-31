package com.cdilansrod.apirestlansrod.web.apirest;

import com.cdilansrod.apirestlansrod.dto.ContratDTO;
import com.cdilansrod.apirestlansrod.dto.OullaResponse;
import com.cdilansrod.apirestlansrod.entities.Contrat;
import com.cdilansrod.apirestlansrod.services.ContratService;
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
public class ContratResource {

    private final Logger log = LoggerFactory.getLogger(ContratResource.class);
    private final ContratService contratService;

    public ContratResource(ContratService contratService) {
        this.contratService = contratService;
    }

    /*====================================================
     * CREER UN TYPE DE CONTRAT
     * ===============================================*/
    @PostMapping("/typecontrats")
    @PreAuthorize("hasRole('ADMIN')")
    public OullaResponse<Contrat> createContrat(@Valid @RequestBody ContratDTO contratDTO) throws URISyntaxException {
        log.debug("Requête pour créer un type de contrat : {}", contratDTO);
        OullaResponse<Contrat> response = new OullaResponse<>();
        try {
            if (contratDTO.getId() != null) {
                log.info("Une nouvau type de contrat ne peut pas déjà avoir ID");
            } else if (contratDTO !=null){
                Contrat result = contratService.save(contratDTO);
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
     * MODIFIER UN TYPE DE CONTRAT
     * ===============================================*/
    @PutMapping("/typecontrats")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public OullaResponse<Optional<ContratDTO>> updateContrat(@Valid @RequestBody ContratDTO contratDTO) throws URISyntaxException {
        log.debug("Requête pour modifier une entreprise : {}", contratDTO);
        OullaResponse<Optional<ContratDTO>> response = new OullaResponse<>();
        try {
            if (contratDTO.getId() == null) {
                log.info("ID invalide il ne doit pas être vide");
            }else {
                Optional<ContratDTO> updatedContrat = contratService.updateContrat(contratDTO);
                log.debug("le contenu de  updatedContrat: {}", updatedContrat);
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(updatedContrat);
            }
        } catch (Exception ex) {
            log.info("########## MODIFICATION CONTRAT : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            System.out.println(ex.getMessage());
        }
        return response;
    }

    /*====================================================
     * OBTENIR UN TYPE DE CONTRAT
     * ===============================================*/
    @GetMapping("/typecontrats/{id}")
    public  OullaResponse<Optional<ContratDTO>> getContrat(@PathVariable Long id) {
        log.debug("Requête pour trouver un contrat : {}", id);
        OullaResponse<Optional<ContratDTO>> response = new OullaResponse<>();
        try {
            Optional<ContratDTO> checkOneContrat =  contratService.findOne(id).map(ContratDTO::new);
            log.debug("le contenu de  checkOneContrat: {}", checkOneContrat);
            if (checkOneContrat != null) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(checkOneContrat);
            }
        } catch (Exception ex) {
            log.info("########## OBTENIR UN CONTRAT : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            System.out.println(ex.getMessage());
        }
        return response;
    }


    /*====================================================
     * OBTENIR TOUS LES TYPES DE CONTRAT
     * ===============================================*/
    @GetMapping("/typecontrats")
    public OullaResponse<Page<Contrat>> getAllContrat(Pageable pageable) {
        log.debug("Requête pour obtenir les Contrats");
        OullaResponse<Page<Contrat>> response = new OullaResponse<>();
        try {
            Page<Contrat> page = contratService.findAll(pageable);
            log.info("la liste des Contrats= "+page.getContent());
            if (!page.isEmpty()) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(page);
            }
        } catch (Exception ex) {
            log.info("########## LISTE DES CONTRATS : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            log.info("########## MESSAGE D'EXCEPTION : " + ex.getMessage());
        }
        return response;
    }


    /*====================================================
    * SUPPRIMER UN TYPE DE CONTRAT
    * ===============================================*/
    @DeleteMapping("/typecontrats/{id}")
    public OullaResponse<Optional<ContratDTO>> deleteContrat(@PathVariable Long id) {
        log.debug("Requete pour supprimer un contrat : {}", id);
        OullaResponse<Optional<ContratDTO>> response = new OullaResponse<>();
        try {
            Optional<ContratDTO> checkOneContrat =  contratService.findOne(id).map(ContratDTO::new);
            log.info("########## Item contrat à supprimer= "+checkOneContrat.toString());
            if (checkOneContrat != null) {
                contratService.delete(id);
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(checkOneContrat);
            }
        } catch (Exception ex) {
            log.info("########## ITEM CONTRAT SUPPRIME: " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setStatus(false);
            response.setCode(Utility.CODE_FAILED);
            response.setMessage(Utility.MESSAGE_FAILED + " - " + ex.getMessage());
        }
        return response;
    }
}
