/**
 * 
 */
package dev.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestParam;

import dev.entities.Collegue;

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

	public List<Collegue> rechercherParNom(@RequestParam("nom") String nomRecherche)
	{
		return new ArrayList<Collegue>(
				data.values().stream().filter(t -> t.getNom().equals(nomRecherche)).collect(Collectors.toList()));
	}

	/**
	 * Getter
	 * 
	 * @return the data
	 */
	public Map<String, Collegue> getData()
	{
		return data;
	}

	/**
	 * Setter
	 * 
	 * @param data the data to set
	 */
	public void setData(Map<String, Collegue> data)
	{
		this.data = data;
	}
}
