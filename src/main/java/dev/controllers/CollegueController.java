/**
 * 
 */
package dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entities.Collegue;
import dev.entities.CollegueACompleter;
import dev.entities.CollegueMatriculePhoto;
import dev.entities.ColleguePojo;
import dev.services.CollegueService;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@RestController
@RequestMapping("/collegues")
@CrossOrigin
public class CollegueController {
    @Autowired
    private CollegueService collegueService;

    @GetMapping()
    public List<String> afficherCollegueParNom(@RequestParam("nom") String nomRecherche) {
	return collegueService.rechercherParNom(nomRecherche);
    }

    @GetMapping(path = "/{matriculeRecherche}")
    public Collegue afficherCollegueParMatricule(@PathVariable String matriculeRecherche) {
	return collegueService.rechercherParMatricule(matriculeRecherche);
    }

    @GetMapping(path = "/collegues")
    public List<Collegue> afficherCollegues() {
	return collegueService.rechercherCollegues();
    }

    @GetMapping(path = "/photos")
    public List<CollegueMatriculePhoto> afficherPhotosEtMatricules() {
	return collegueService.rechercherGallerie();
    }

    @GetMapping(path = "/matricules")
    public List<String> afficherMatricules() {
	return collegueService.rechercherMatricules();
    }

    @PostMapping
    public ResponseEntity<ColleguePojo> ajouterCollegue(@RequestBody ColleguePojo collegueAAjouter) {
	ColleguePojo collegueTemp = collegueService.ajouterUnCollegue(collegueAAjouter);
	return ResponseEntity.status(HttpStatus.OK).body(collegueTemp);
    }

    @PostMapping(path = "/verifMail")
    public ResponseEntity<Boolean> existingEmail(@RequestBody String email) {
	boolean email1 = collegueService.existingEmail(email);
	return ResponseEntity.status(HttpStatus.OK).body(email1);
    }

    @PatchMapping(path = "/{matriculeRecherche}")
    public ResponseEntity<Collegue> miseAJourCollegue(@PathVariable String matriculeRecherche,
	    @RequestBody CollegueACompleter nvCollegue) {
	Collegue collegueTemp = new Collegue();

	if (nvCollegue.getEmail() != null) {
	    collegueTemp = collegueService.modifierEmail(matriculeRecherche, nvCollegue.getEmail());
	}

	if (nvCollegue.getPhotoUrl() != null) {
	    collegueTemp = collegueService.modifierPhotoUrl(matriculeRecherche, nvCollegue.getPhotoUrl());
	}

	return ResponseEntity.status(HttpStatus.OK).body(collegueTemp);
    }
}
