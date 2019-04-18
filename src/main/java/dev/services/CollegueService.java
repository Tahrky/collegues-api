/**
 * 
 */
package dev.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.entities.Collegue;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouveException;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueService
{
	private Map<String, Collegue> data = new HashMap<>();
	final String TIME_PATTERN = "yyyy-MM-d";

	public CollegueService()
	{
		String matriculeTemp = UUID.randomUUID().toString();
		LocalDate date1 = LocalDate.parse("1950-11-14", DateTimeFormatter.ofPattern(TIME_PATTERN));

		data.put(matriculeTemp,
				new Collegue(matriculeTemp, "Bob", "Robert", "a@a.a", "http://photoQuiExistePas.com", date1));
		matriculeTemp = UUID.randomUUID().toString();
		data.put(matriculeTemp,
				new Collegue(matriculeTemp, "Adeline", "Noelle", "b@b.b", "http://photoQuiExistePas.com", date1));
		matriculeTemp = UUID.randomUUID().toString();
		data.put(matriculeTemp,
				new Collegue(matriculeTemp, "Bob", "Arthur", "c@c.c", "http://photoQuiExistePas.com", date1));

		data.put("1", new Collegue("1", "Bobbi", "Albert", "a@a.a", "http://photoQuiExistePas.com", date1));
	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter)
	{
		if (collegueAAjouter.getNom().length() <= 2)
		{
			throw new CollegueInvalideException("Nom invalide, trop court (2 caractères minimums)");
		}

		if (collegueAAjouter.getPrenoms().length() <= 2)
		{
			throw new CollegueInvalideException("Prénom invalide, trop court (2 caractères minimums)");
		}

		if (collegueAAjouter.getEmail().length() <= 3)
		{
			throw new CollegueInvalideException("Email invalide, trop court (3 caractères minimums)");
		}

		if (!collegueAAjouter.getEmail().contains("@"))
		{
			throw new CollegueInvalideException("Email invalide, doit contenir un @");
		}

		LocalDate estMajeur = LocalDate.now();
		Period period = Period.between(collegueAAjouter.getDateDeNaissance(), estMajeur);
		if (period.getYears() < 18)
		{
			throw new CollegueInvalideException("Vous devez être majeur pour vous inscrire");
		}

		if (!collegueAAjouter.getPhotoUrl().contains("http"))
		{
			throw new CollegueInvalideException(
					"Url de la photo invalide, doit contenir au moins http en début de lien");
		}

		collegueAAjouter.setMatricule(UUID.randomUUID().toString());
		data.put(collegueAAjouter.getMatricule(), collegueAAjouter);
		return collegueAAjouter;
	}

	public Collegue modifierEmail(String matricule, String email)
	{
		if (!data.containsKey(matricule))
		{
			throw new CollegueNonTrouveException();
		}

		Collegue collegue = data.get(matricule);

		if (email.length() <= 3)
		{
			throw new CollegueInvalideException("Email invalide, trop court (3 caractères minimums)");
		}

		if (!email.contains("@"))
		{
			throw new CollegueInvalideException("Email invalide, doit contenir un @");
		}

		collegue.setEmail(email);
		data.put(matricule, collegue);

		return collegue;
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl)
	{
		if (!data.containsKey(matricule))
		{
			throw new CollegueNonTrouveException();
		}

		Collegue collegue = data.get(matricule);

		if (!photoUrl.contains("http"))
		{
			throw new CollegueInvalideException(
					"Url de la photo invalide, doit contenir au moins http en début de lien");
		}

		collegue.setPhotoUrl(photoUrl);
		data.put(matricule, collegue);

		return collegue;
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
