/**
 * 
 */
package dev.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.entities.Collegue;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Service
public class CollegueService
{
	@Autowired
	CollegueRepository colRepo;

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
		colRepo.save(collegueAAjouter);
		return collegueAAjouter;
	}

	public Collegue modifierEmail(String matricule, String email)
	{
		if (!colRepo.existsById(matricule))
		{
			throw new CollegueNonTrouveException();
		}

		Collegue collegue = colRepo.getOne(matricule);

		if (email.length() <= 3)
		{
			throw new CollegueInvalideException("Email invalide, trop court (3 caractères minimums)");
		}

		if (!email.contains("@"))
		{
			throw new CollegueInvalideException("Email invalide, doit contenir un @");
		}

		collegue.setEmail(email);
		colRepo.save(collegue);

		return collegue;
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl)
	{
		if (!colRepo.existsById(matricule))
		{
			throw new CollegueNonTrouveException();
		}

		Collegue collegue = colRepo.getOne(matricule);

		if (!photoUrl.contains("http"))
		{
			throw new CollegueInvalideException(
					"Url de la photo invalide, doit contenir au moins http en début de lien");
		}

		collegue.setPhotoUrl(photoUrl);
		colRepo.save(collegue);

		return collegue;
	}

	public List<Collegue> rechercherParNom(String nomRecherche)
	{
		return colRepo.findAll().stream().filter(t -> t.getNom().equals(nomRecherche)).collect(Collectors.toList());
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException
	{
		return Optional.ofNullable(colRepo.getOne(matriculeRecherche)).orElseThrow(CollegueNonTrouveException::new);
	}
}
