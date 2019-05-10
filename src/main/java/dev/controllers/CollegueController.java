/**
 * 
 */
package dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entities.CollegueDTO;
import dev.entities.CollegueEmailPhoto;
import dev.entities.CollegueMatriculeNote;
import dev.entities.CollegueMatriculePhoto;
import dev.entities.NoteDTO;
import dev.services.CollegueService;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@RestController
@RequestMapping("/collegues")
//@CrossOrigin
public class CollegueController {
    @Autowired
    private CollegueService collegueService;

    // Collegues ---------------------------------------
    // Retour d'informations pour affichage ------------
    
    @GetMapping()
    public List<String> afficherCollegueParNom(@RequestParam("nom") String nomRecherche) {
	return collegueService.rechercherParNom(nomRecherche);
    }

    @GetMapping(path = "/{matriculeRecherche}")
    public CollegueDTO afficherCollegueParMatricule(@PathVariable String matriculeRecherche) {
	return collegueService.rechercherParMatricule(matriculeRecherche);
    }

    // Affiche tous les collegues de la BDD
    @GetMapping(path = "/collegues")
    public List<CollegueDTO> afficherCollegues() {
	return collegueService.rechercherCollegues();
    }
    
    // Affiche tous les matricules de la BDD
    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/matricules")
    public List<String> afficherMatricules() {
	return collegueService.rechercherMatricules();
    }

    // Affichez toutes les matricules des collègues et leur photo associée
    @GetMapping(path = "/photos")
    public List<CollegueMatriculePhoto> afficherPhotosEtMatricules() {
	return collegueService.rechercherGallerie();
    }

    // Opérations sur la BDD (Ajout, modif, vérifications ...)
    
    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<CollegueDTO> ajouterCollegue(@RequestBody CollegueDTO collegueAAjouter) {
	CollegueDTO collegueTemp = collegueService.ajouterUnCollegue(collegueAAjouter);
	return ResponseEntity.status(HttpStatus.OK).body(collegueTemp);
    }
    
    @Secured("ROLE_ADMIN")
    @PatchMapping(path = "/{matriculeRecherche}")
    public ResponseEntity<CollegueDTO> miseAJourCollegue(@PathVariable String matriculeRecherche,
	    @RequestBody CollegueEmailPhoto nvCollegue) {
	CollegueDTO collegueTemp = new CollegueDTO();

	if (nvCollegue.getEmail() != null) {
	    collegueTemp = collegueService.modifierEmail(matriculeRecherche, nvCollegue.getEmail());
	}

	if (nvCollegue.getPhotoUrl() != null) {
	    collegueTemp = collegueService.modifierPhotoUrl(matriculeRecherche, nvCollegue.getPhotoUrl());
	}

	CollegueDTO colleguePojo = new CollegueDTO(collegueTemp.getMatricule(), collegueTemp.getNom(),
		collegueTemp.getPrenoms(), collegueTemp.getEmail(), collegueTemp.getPhotoUrl(),
		collegueTemp.getDateDeNaissance());

	return ResponseEntity.status(HttpStatus.OK).body(colleguePojo);
    }

    @PostMapping(path = "/verifMail")
    public ResponseEntity<Boolean> existingEmail(@RequestBody String email) {
	boolean email1 = collegueService.existingEmail(email);
	return ResponseEntity.status(HttpStatus.OK).body(email1);
    }

    // Notes d'un collègue --------------------------------
    // Retour d'informations pour affichage ---------------
    
    @GetMapping(path = "/notes/{matriculeRecherche}")
    public List<NoteDTO> afficherNotesParMatricule(@PathVariable String matriculeRecherche) {
	return collegueService.rechercherNotesParMatricules(matriculeRecherche);
    }
    
    // Opération sur la BDD (ajout et suppression)
    
    @PostMapping(path = "/ajoutNote")
    public ResponseEntity<Boolean> ajoutNote(@RequestBody CollegueMatriculeNote collegue) {
	boolean ajout = collegueService.ajoutNote(collegue);
	return ResponseEntity.status(HttpStatus.OK).body(ajout);
    }

    @PostMapping(path = "/supprNote")
    public ResponseEntity<Boolean> supprNote(@RequestBody int id) {
	collegueService.supprNote(id);
	return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
