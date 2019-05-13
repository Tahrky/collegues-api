/**
 *
 */
package dev.entities;

import java.util.List;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueEmailNomPrenomsPhotoUrlRoles {
    String email;
    String nom;
    String prenoms;
    String photoUrl;
    List<String> roles;

    public CollegueEmailNomPrenomsPhotoUrlRoles() {}

    public CollegueEmailNomPrenomsPhotoUrlRoles(String email, String nom, String penoms, String photoUrl, List<String> roles) {
	super();
	this.email = email;
	this.nom = nom;
	this.prenoms = penoms;
	this.photoUrl = photoUrl;
	this.roles = roles;
    }

    /**
     * Getter
     * @return the matricule
     */
    public String getEmail() {
	return email;
    }
    /**
     * Setter
     * @param matricule the matricule to set
     */
    public void setEmail(String email) {
	this.email = email;
    }
    /**
     * Getter
     * @return the nom
     */
    public String getNom() {
	return nom;
    }
    /**
     * Setter
     * @param nom the nom to set
     */
    public void setNom(String nom) {
	this.nom = nom;
    }
    /**
     * Getter
     * @return the penoms
     */
    public String getPrenoms() {
	return prenoms;
    }
    /**
     * Setter
     * @param penoms the penoms to set
     */
    public void setPrenoms(String penoms) {
	this.prenoms = penoms;
    }
    /**
	 * Getter
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * Setter
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
     * Getter
     * @return the roles
     */
    public List<String> getRoles() {
	return roles;
    }
    /**
     * Setter
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles) {
	this.roles = roles;
    }
}
