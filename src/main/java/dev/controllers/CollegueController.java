/**
 * 
 */
package dev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import dev.entities.CollegueACompleter;
import dev.entities.ColleguePojo;
import dev.services.CollegueService;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@RestController
@RequestMapping("/collegues")
public class CollegueController
{
	@Autowired
	private CollegueService collegueService;

	@GetMapping()
	public List<Collegue> afficherCollegueParNom(@RequestParam("nom") String nomRecherche)
	{
		return collegueService.rechercherParNom(nomRecherche);
	}

	@GetMapping(path = "/{matriculeRecherche}")
	public Collegue afficherCollegueParMatricule(@PathVariable String matriculeRecherche)
	{
		return collegueService.rechercherParMatricule(matriculeRecherche);
	}

	@PostMapping
	public ResponseEntity<ColleguePojo> ajouterCollegue(@RequestBody ColleguePojo collegueAAjouter)
	{
		ColleguePojo collegueTemp = collegueService.ajouterUnCollegue(collegueAAjouter);
		return ResponseEntity.status(HttpStatus.OK).body(collegueTemp);
	}

	@PatchMapping(path = "/{matriculeRecherche}")
	public ResponseEntity<Collegue> miseAJourCollegue(@PathVariable String matriculeRecherche,
			@RequestBody CollegueACompleter nvCollegue)
	{
		Collegue collegueTemp = new Collegue();

		if (nvCollegue.getEmail() != null)
		{
			collegueTemp = collegueService.modifierEmail(matriculeRecherche, nvCollegue.getEmail());
		}

		if (nvCollegue.getPhotoUrl() != null)
		{
			collegueTemp = collegueService.modifierPhotoUrl(matriculeRecherche, nvCollegue.getPhotoUrl());
		}

		return ResponseEntity.status(HttpStatus.OK).body(collegueTemp);
	}
}
