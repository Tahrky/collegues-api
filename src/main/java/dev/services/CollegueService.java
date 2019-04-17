/**
 * 
 */
package dev.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.entities.Collegue;
import dev.exception.CollegueNonTrouveException;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueService
{
	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService()
	{
		String matriculeTemp = UUID.randomUUID().toString();
		data.put(matriculeTemp, new Collegue(matriculeTemp, "Bob", "Robert", "a@a.a", "1950-11_14", ""));
		matriculeTemp = UUID.randomUUID().toString();
		data.put(matriculeTemp, new Collegue(matriculeTemp, "Adeline", "Noelle", "b@b.b", "1956-08_14", ""));
		matriculeTemp = UUID.randomUUID().toString();
		data.put(matriculeTemp, new Collegue(matriculeTemp, "Bob", "Arthur", "c@c.c", "1946-08_03", ""));
	}

	public List<Collegue> rechercherParNom(String nomRecherche)
	{
		return data.values().stream().filter(t -> t.getNom().equals(nomRecherche)).collect(Collectors.toList());
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException
	{
		return Optional.ofNullable(data.get(matriculeRecherche)).orElseThrow(CollegueNonTrouveException::new);
	}
}
