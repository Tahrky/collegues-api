/**
 * 
 */
package dev.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.entities.Collegue;
import dev.entities.ColleguePojo;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Service
public class CollegueService {
    @Autowired
    private CollegueRepository colRepo;

    /**
     * Getter
     * 
     * @return the colRepo
     */
    public CollegueRepository getColRepo() {
	return colRepo;
    }

    /**
     * Setter
     * 
     * @param colRepo the colRepo to set
     */
    public void setColRepo(CollegueRepository colRepo) {
	this.colRepo = colRepo;
    }

    public ColleguePojo ajouterUnCollegue(ColleguePojo collegueAAjouter) {
	if (collegueAAjouter.getNom().length() <= 2 && collegueAAjouter.getNom().length() > 255) {
	    throw new CollegueInvalideException("Nom invalide, trop court (2 caractères minimums)");
	}

	if (collegueAAjouter.getPrenoms().length() <= 2 && collegueAAjouter.getPrenoms().length() > 255) {
	    throw new CollegueInvalideException("Prénom invalide, trop court (2 caractères minimums)");
	}

	if (collegueAAjouter.getEmail().length() <= 3 && collegueAAjouter.getEmail().length() > 255) {
	    throw new CollegueInvalideException("Email invalide, trop court (3 caractères minimums)");
	}

	if (!collegueAAjouter.getEmail().contains("@")) {
	    throw new CollegueInvalideException("Email invalide, doit contenir un @");
	}

	LocalDate estMajeur = LocalDate.now();
	Period period = Period.between(collegueAAjouter.getDateDeNaissance(), estMajeur);
	if (period.getYears() < 18) {
	    throw new CollegueInvalideException("Vous devez être majeur pour vous inscrire");
	}

	if (!collegueAAjouter.getPhotoUrl().startsWith("http") && collegueAAjouter.getPhotoUrl().length() > 255) {
	    throw new CollegueInvalideException(
		    "Url de la photo invalide, doit contenir au moins http en début de lien");
	}

	collegueAAjouter.setMatricule(UUID.randomUUID().toString());
	colRepo.save(collegueAAjouter.transformerEnCollegue());
	return collegueAAjouter;
    }

    public Collegue modifierEmail(String matricule, String email) {
	Collegue collegue = colRepo.findById(matricule).orElseThrow(CollegueNonTrouveException::new);

	if (email.length() <= 3) {
	    throw new CollegueInvalideException("Email invalide, trop court (3 caractères minimums)");
	}

	if (!email.contains("@")) {
	    throw new CollegueInvalideException("Email invalide, doit contenir un @");
	}

	collegue.setEmail(email);
	colRepo.save(collegue);

	return collegue;
    }

    public Collegue modifierPhotoUrl(String matricule, String photoUrl) {
	Collegue collegue = colRepo.findById(matricule).orElseThrow(CollegueNonTrouveException::new);

	if (!photoUrl.contains("http") && photoUrl.length() > 255) {
	    throw new CollegueInvalideException(
		    "Url de la photo invalide, doit contenir au moins http en début de lien");
	}

	collegue.setPhotoUrl(photoUrl);
	colRepo.save(collegue);

	return collegue;
    }

    public List<String> rechercherParNom(String nomRecherche) {
	return colRepo.findAll().stream().filter(t -> t.getNom().equals(nomRecherche)).map(c -> c.getMatricule())
		.collect(Collectors.toList());
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) {
	return colRepo.findById(matriculeRecherche).orElseThrow(CollegueNonTrouveException::new);
    }

    public List<Collegue> rechercherCollegues() {
	return colRepo.findAll();
    }

    public boolean existingEmail(String email) {
	return !colRepo.findByEmail(email).isEmpty();
    }

    /**
     * @return
     */
    public List<String> rechercherMatricules() {
	return colRepo.findAll().stream().map(collegue -> collegue.getMatricule()).collect(Collectors.toList());
    }
}
