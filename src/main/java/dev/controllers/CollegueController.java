/**
 * 
 */
package dev.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.entities.Collegue;
import dev.services.CollegueService;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@RestController
@RequestMapping("/collegues")
public class CollegueController
{
	private CollegueService collegueService = new CollegueService();

	@GetMapping()
	public List<Collegue> afficherCollegueParNom(@RequestParam("nom") String nomRecherche)
	{
		return collegueService.rechercherParNom(nomRecherche);
	}

	@GetMapping(path = "/{matricule}")
	public Collegue afficherCollegueParMatricule(@PathVariable String matriculeRecherche)
	{
		return collegueService.rechercherParMatricule(matriculeRecherche);
	}

	@PostMapping
	public ResponseEntity<Collegue> ajouterCollegue(@RequestBody Collegue collegueAAjouter)
	{
		Collegue collegueTemp = collegueService.ajouterUnCollegue(collegueAAjouter);
		return ResponseEntity.status(HttpStatus.OK).body(collegueTemp);
	}

	@PatchMapping
	public ResponseEntity<String> miseAJourEmail(@RequestParam("matricule") String matriculeRecherche,
			@RequestParam("email") String nvlEmail)
	{
		collegueService.modifierEmail(matriculeRecherche, nvlEmail);
		return ResponseEntity.status(HttpStatus.OK).body("Email modifié correctement");
	}
}
