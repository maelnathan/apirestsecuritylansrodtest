package com.cdilansrod.apirestlansrod.web.apirest;

import com.cdilansrod.apirestlansrod.dto.OullaResponse;
import com.cdilansrod.apirestlansrod.dto.SalarieDTO;
import com.cdilansrod.apirestlansrod.entities.Salarie;
import com.cdilansrod.apirestlansrod.repositories.SalarieRepository;
import com.cdilansrod.apirestlansrod.services.SalarieService;
import com.cdilansrod.apirestlansrod.tools.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Optional;


@RestController
@RequestMapping(Utility.URL_API_LANSROD)
public class SalarieResource {

    private final Logger log = LoggerFactory.getLogger(SalarieResource.class);
    private final SalarieService salarieService;
    private final SalarieRepository salarieRepository;

    public SalarieResource(SalarieService salarieService, SalarieRepository salarieRepository) {
        this.salarieService = salarieService;
        this.salarieRepository = salarieRepository;
    }

    /*====================================================
     * CREER UN SALARIE
     * ===============================================*/
    @PostMapping("/salaries")
    @PreAuthorize("hasRole('ADMIN')")
    public OullaResponse<Salarie> createSalarie(@Valid @RequestBody SalarieDTO salarieDTO) throws URISyntaxException {
        log.debug("Requête pour enregistrer un salarié : {}", salarieDTO);
        OullaResponse<Salarie> response = new OullaResponse<>();
        try {
            if (salarieDTO.getId() != null) {
                log.info("Une nouveau salarie ne peut pas déjà avoir ID");
            } else if (salarieDTO !=null){
                Salarie result = salarieService.save(salarieDTO);
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
     * MODIFIER UN SALARIE
     * ===============================================*/
    @PutMapping("/salaries")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public OullaResponse<Optional<SalarieDTO>> updateSalarie(@Valid @RequestBody SalarieDTO salarieDTO) throws URISyntaxException {
        log.debug("Requête pour modifier un employé : {}", salarieDTO);
        OullaResponse<Optional<SalarieDTO>> response = new OullaResponse<>();
        try {
            if (salarieDTO.getId() == null) {
                log.info("ID invalide il ne doit pas être vide");
            }else {
                Optional<SalarieDTO> updatedSalarie = salarieService.updateSalarie(salarieDTO);
                log.debug("le contenu de  updatedCity: {}", updatedSalarie);
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(updatedSalarie);
            }
        } catch (Exception ex) {
            log.info("########## MODIFICATION SALARIE : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            System.out.println(ex.getMessage());
        }
        return response;
    }

    /*====================================================
     * OBTENIR UN SALARIE
     * ===============================================*/
    @GetMapping("/salaries/{id}")
    public  OullaResponse<Optional<SalarieDTO>> getSalarie(@PathVariable Long id) {
        log.debug("Requête pour trouver un salarié : {}", id);
        OullaResponse<Optional<SalarieDTO>> response = new OullaResponse<>();
        try {
            Optional<SalarieDTO> checkOneSalarie =  salarieService.findOne(id).map(SalarieDTO::new);
            log.debug("le contenu de  checkOneDistricts: {}", checkOneSalarie);
            if (checkOneSalarie != null) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(checkOneSalarie);
            }
        } catch (Exception ex) {
            log.info("########## OBTENIR UN SALARIE : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            System.out.println(ex.getMessage());
        }
        return response;
    }

    /*====================================================
     * OBTENIR LES SALARIES
     * ===============================================*/
    @GetMapping("/salaries")
    public OullaResponse<Page<Salarie>> getAllSalarie(Pageable pageable) {
        log.debug("Requête pour obtenir les salariés");
        OullaResponse<Page<Salarie>> response = new OullaResponse<>();
        try {
            Page<Salarie> page = salarieService.findAll(pageable);
            log.info("la liste des salariés= "+page.getContent());
            if (!page.isEmpty()) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(page);
            }
        } catch (Exception ex) {
            log.info("########## LISTE DES SALARIE : " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setMessage(Utility.MESSAGE_FAILED);
            response.setCode(Utility.CODE_FAILED);
            response.setStatus(false);
            log.info("########## MESSAGE D'EXCEPTION : " + ex.getMessage());
        }
        return response;
    }


    /*====================================================
     * SUPPRIMER UN SALARIE
     * ===============================================*/
    @DeleteMapping("/salaries/{id}")
    public OullaResponse<Optional<SalarieDTO>> deleteSalarie(@PathVariable Long id) {
        log.debug("Requete pour supprimer un Salarie : {}", id);
        OullaResponse<Optional<SalarieDTO>> response = new OullaResponse<>();
        try {
            Optional<SalarieDTO> checkOneSalarie =  salarieService.findOne(id).map(SalarieDTO::new);
            log.info("########## Item salarie à supprimer= "+checkOneSalarie.toString());
            if (checkOneSalarie != null) {
                salarieService.delete(id);
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(checkOneSalarie);
            }
        } catch (Exception ex) {
            log.info("########## ITEM VILLE SUPPRIME: " + Utility.MESSAGE_FAILED);
            response.setRecord(null);
            response.setStatus(false);
            response.setCode(Utility.CODE_FAILED);
            response.setMessage(Utility.MESSAGE_FAILED + " - " + ex.getMessage());
        }
        return response;
    }


    /*==================================================
     * RECHERCHER DES SALARIES PAR MOT CLE
     * ===============================================*/
        @GetMapping(value = "/getSalariesByParam")
        public OullaResponse<Page<Salarie>> getListWithSearchParam(@RequestParam(required = false, defaultValue = "") Long entrepriseId,
                                                                   @RequestParam(required = false, defaultValue = "") Long contratId,
                                                                   @RequestParam(required = false, defaultValue = "") Instant dateEmbauche,
                                                                   @RequestParam(required = false, defaultValue = "") BigDecimal salaire,
                                                                   Pageable pageable) {
            OullaResponse<Page<Salarie>> response = new OullaResponse<>();
            try {
                Page<Salarie> page = salarieRepository.getList(entrepriseId,
                                                               contratId,
                                                               dateEmbauche,
                                                               salaire,
                                                               pageable);
                if (!page.isEmpty() && page.getSize()>0) {
                    response.setStatus(true);
                    response.setCode(Utility.CODE_SUCCESS);
                    response.setMessage(Utility.MESSAGE_SUCCESS);
                    response.setRecord(page);
                }
            } catch (Exception ex) {
                response.setStatus(false);
                response.setCode(Utility.CODE_FAILED);
                response.setMessage(Utility.MESSAGE_FAILED + " - " + ex.getMessage());
                response.setRecord(null);
            }
            return response;
        }

    /*====================================================
     * OBTENIR LES SALARIES
     * ===============================================
    @GetMapping(value = "/getAllDetailEntrepriseByTypeContrat")
    public OullaResponse<List<Salarie>> getAllDetailByTypeContratId(@RequestParam Long id) {
        OullaResponse<List<Salarie>> response = new OullaResponse<>();
        try {
            List<Salarie> listSalarie = (List<Salarie>) salarieRepository.findAll();
            // Filtrer la liste des salariés par type de contrat
            listSalarie = listSalarie.stream().filter(it->it.getContrat().getId().equals(id)).collect(Collectors.toList());

            if (!listSalarie.isEmpty() && listSalarie.size() > 0) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(listSalarie);
            }
        } catch (Exception ex) {
            response.setStatus(false);
            response.setCode(Utility.CODE_FAILED);
            response.setMessage(Utility.MESSAGE_FAILED + " - " + ex.getMessage());
            response.setRecord(null);
        }
        return response;
    }*/

    /*===========================================================================
     * RECHERCHER SALAIRE MIN, MAX ET MOYEN VERSE PAR UNE ENTREPRISE PAR CONTRAT
     * =========================================================================*/
    @GetMapping(value = "/getSalarieMinMaxMoyByEntrepriseByTypeContrat")
    public OullaResponse<Page<Salarie>> getListMinMaxMoyByEntrepriseByTypeContrat(@RequestParam(required = false, defaultValue = "1") Long entrepriseId,
                                                                                  @RequestParam(required = false, defaultValue = "1") Long contratId,
                                                                                  Pageable pageable) {
        OullaResponse<Page<Salarie>> response = new OullaResponse<>();
        try {
            Page<Salarie> page = salarieRepository.getListDetail(entrepriseId,contratId,pageable);
            if (!page.isEmpty() && page.getSize()>0) {
                response.setStatus(true);
                response.setCode(Utility.CODE_SUCCESS);
                response.setMessage(Utility.MESSAGE_SUCCESS);
                response.setRecord(page);
            }
        } catch (Exception ex) {
            response.setStatus(false);
            response.setCode(Utility.CODE_FAILED);
            response.setMessage(Utility.MESSAGE_FAILED + " - " + ex.getMessage());
            response.setRecord(null);
        }
        return response;
    }

}
