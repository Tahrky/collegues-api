/**
 * 
 */
package dev.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Entity
public class Collegue {
    @Transient
    static final String TIME_PATTERN = "yyyy-MM-d";

    @Id
    String matricule;
    @Column
    String nom;
    @Column
    String prenoms;
    @Column
    String email;
    @Column
    String photoUrl;
    @Column
    LocalDate dateDeNaissance;

    @OneToMany(mappedBy = "collegue")
    private List<Note> noteTab;
    
    @OneToOne (mappedBy="collegue")
    UtilisateurSession utilisateur;

    public boolean addNote(Note note) {
	int tailleList = noteTab.size();
	this.noteTab.add(note);

	boolean retour = false;
	if (tailleList < noteTab.size()) {
	    retour = true;
	}

	return retour;
    }

    public List<NoteDTO> noteToNotePojo() {
	return noteTab.stream().map(note -> new NoteDTO(note.getId(), note.getMessage(), note.getDate()))
		.collect(Collectors.toList());
    }

    public Collegue() {
    }

    public Collegue(String nom, String prenoms, String email, String photoUrl, LocalDate dateDeNaissance) {
	super();
	this.nom = nom.toLowerCase();
	this.prenoms = prenoms;
	this.email = email;
	this.dateDeNaissance = dateDeNaissance;
	this.photoUrl = photoUrl;
    }

    public Collegue(String matricule, String nom, String prenoms, String email, String photoUrl,
	    LocalDate dateDeNaissance) {
	super();
	this.matricule = matricule;
	this.nom = nom.toLowerCase();
	this.prenoms = prenoms;
	this.email = email;
	this.dateDeNaissance = dateDeNaissance;
	this.photoUrl = photoUrl;
    }

    /**
     * Getter
     * 
     * @return the matricule
     */
    public String getMatricule() {
	return matricule;
    }

    /**
     * Setter
     * 
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule) {
	this.matricule = matricule;
    }

    /**
     * Getter
     * 
     * @return the nom
     */
    public String getNom() {
	return nom;
    }

    /**
     * Setter
     * 
     * @param nom the nom to set
     */
    public void setNom(String nom) {
	this.nom = nom.toLowerCase();
    }

    /**
     * Getter
     * 
     * @return the prenoms
     */
    public String getPrenoms() {
	return prenoms;
    }

    /**
     * Setter
     * 
     * @param prenoms the prenoms to set
     */
    public void setPrenoms(String prenoms) {
	this.prenoms = prenoms;
    }

    /**
     * Getter
     * 
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * Setter
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * Getter
     * 
     * @return the dateDeNaissance
     */
    public LocalDate getDateDeNaissance() {
	return dateDeNaissance;
    }

    /**
     * Setter
     * 
     * @param dateDeNaissance the dateDeNaissance to set
     */
    public void setDateDeNaissance(LocalDate dateDeNaissance) {
	this.dateDeNaissance = dateDeNaissance;
    }

    /**
     * Getter
     * 
     * @return the photoUrl
     */
    public String getPhotoUrl() {
	return photoUrl;
    }

    /**
     * Setter
     * 
     * @param photoUrl the photoUrl to set
     */
    public void setPhotoUrl(String photoUrl) {
	this.photoUrl = photoUrl;
    }

    /**
     * Getter
     * 
     * @return the noteTab
     */
    public List<Note> getNoteTab() {
	return noteTab;
    }

    /**
     * Setter
     * 
     * @param noteTab the noteTab to set
     */
    public void setNoteTab(List<Note> noteTab) {
	this.noteTab = noteTab;
    }

}
