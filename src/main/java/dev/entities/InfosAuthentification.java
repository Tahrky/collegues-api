/**
 *
 */
package dev.entities;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class InfosAuthentification {

    private String email;

    private String motDePasse;

    public String getEmail() {
	return email;
    }

    public void setEmail(String nomUtilisateur) {
	this.email = nomUtilisateur;
    }

    public String getMotDePasse() {
	return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }
}
