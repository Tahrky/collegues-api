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
import dev.entities.CollegueDTO;
import dev.entities.CollegueMatriculeNote;
import dev.entities.CollegueMatriculePhoto;
import dev.entities.Note;
import dev.entities.NoteDTO;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouveException;
import dev.repository.CollegueRepository;
import dev.repository.NoteRepository;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Service
public class CollegueService {
    @Autowired
    private CollegueRepository colRepo;

    @Autowired
    private NoteRepository noteRepo;

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

    public CollegueDTO ajouterUnCollegue(CollegueDTO collegueAAjouter) {
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

    public CollegueDTO modifierEmail(String matricule, String email) {
	Collegue collegue = colRepo.findById(matricule).orElseThrow(CollegueNonTrouveException::new);

	if (email.length() <= 3) {
	    throw new CollegueInvalideException("Email invalide, trop court (3 caractères minimums)");
	}

	if (!email.contains("@")) {
	    throw new CollegueInvalideException("Email invalide, doit contenir un @");
	}

	collegue.setEmail(email);
	colRepo.save(collegue);

	return new CollegueDTO(collegue.getMatricule(), collegue.getNom(), collegue.getPrenoms(), collegue.getEmail(), collegue.getPhotoUrl(),
		collegue.getDateDeNaissance());
    }

    public CollegueDTO modifierPhotoUrl(String matricule, String photoUrl) {
	Collegue collegue = colRepo.findById(matricule).orElseThrow(CollegueNonTrouveException::new);

	if (!photoUrl.contains("http") && photoUrl.length() > 255) {
	    throw new CollegueInvalideException(
		    "Url de la photo invalide, doit contenir au moins http en début de lien");
	}

	collegue.setPhotoUrl(photoUrl);
	colRepo.save(collegue);

	return new CollegueDTO(collegue.getMatricule(), collegue.getNom(), collegue.getPrenoms(), collegue.getEmail(), collegue.getPhotoUrl(),
		collegue.getDateDeNaissance());
    }

    public List<String> rechercherParNom(String nomRecherche) {
	return colRepo.findAll().stream().filter(t -> t.getNom().equals(nomRecherche)).map(Collegue::getMatricule)
		.collect(Collectors.toList());
    }

    public CollegueDTO rechercherParMatricule(String matriculeRecherche) {
	Collegue col = colRepo.findById(matriculeRecherche).orElseThrow(CollegueNonTrouveException::new);
	return new CollegueDTO(col.getMatricule(), col.getNom(), col.getPrenoms(), col.getEmail(), col.getPhotoUrl(),
		col.getDateDeNaissance());
    }

    public List<CollegueDTO> rechercherCollegues() {
	return colRepo.findAll().stream().map(col -> new CollegueDTO(col.getMatricule(), col.getNom(),
		col.getPrenoms(), col.getEmail(), col.getPhotoUrl(), col.getDateDeNaissance()))
		.collect(Collectors.toList());
    }
    
    /**
     * @return
     */
    public List<String> rechercherMatricules() {
	return colRepo.findAll().stream().map(Collegue::getMatricule).collect(Collectors.toList());
    }

    public boolean existingEmail(String email) {
	return !colRepo.findByEmail(email).isEmpty();
    }

    /**
     * @return
     */
    public List<CollegueMatriculePhoto> rechercherGallerie() {

	return colRepo.findAll().stream()
		.map(collegue -> new CollegueMatriculePhoto(collegue.getMatricule(), collegue.getPhotoUrl()))
		.collect(Collectors.toList());
    }

    public boolean ajoutNote(CollegueMatriculeNote collegue) {
	Note noteTemp = new Note(collegue.getMessageNote());

	Collegue colTemp = colRepo.findById(collegue.getMatricule()).orElseThrow(CollegueNonTrouveException::new);
	noteTemp.setCollegue(colTemp);
	colTemp.addNote(noteTemp);
	colRepo.save(colTemp);
	noteRepo.save(noteTemp);

	return colRepo.findById(collegue.getMatricule()).orElseThrow(CollegueNonTrouveException::new).addNote(noteTemp);
    }

    /**
     * @param matriculeRecherche
     * @return
     */
    public List<NoteDTO> rechercherNotesParMatricules(String matriculeRecherche) {
	return colRepo.findById(matriculeRecherche).orElseThrow(CollegueNonTrouveException::new).noteToNotePojo();
    }

    public void supprNote(int id) {
	noteRepo.deleteById(id);
    }

}
