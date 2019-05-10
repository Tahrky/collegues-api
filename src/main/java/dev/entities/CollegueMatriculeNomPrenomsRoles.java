/**
 *
 */
package dev.entities;

import java.util.List;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueMatriculeNomPrenomsRoles {
    String matricule;
    String nom;
    String prenoms;
    List<String> roles;

    public CollegueMatriculeNomPrenomsRoles() {}

    public CollegueMatriculeNomPrenomsRoles(String matricule, String nom, String penoms, List<String> roles) {
	super();
	this.matricule = matricule;
	this.nom = nom;
	this.prenoms = penoms;
	this.roles = roles;
    }

    /**
     * Getter
     * @return the matricule
     */
    public String getMatricule() {
	return matricule;
    }
    /**
     * Setter
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule) {
	this.matricule = matricule;
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
