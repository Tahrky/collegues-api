/**
 * 
 */
package dev.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public List<Collegue> afficherCollegueParNom(@RequestParam("nom") String nomRecherche)
	{
		return collegueService.rechercherParNom(nomRecherche);
	}

	@GetMapping(path = "/{matriculeRecherche}")
	@ResponseBody
	public Collegue afficherCollegueParMatricule(@PathVariable String matriculeRecherche)
	{
		return collegueService.rechercherParMatricule(matriculeRecherche);
	}
}
