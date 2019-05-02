/**
 * 
 */
package dev.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

}
