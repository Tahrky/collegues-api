/**
 *
 */
package dev.entities;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueMatriculePhoto {
    String matricule;
    String photoUrl;

    public CollegueMatriculePhoto() {

    }

    public CollegueMatriculePhoto(String matricule, String photoUrl) {
	this.matricule = matricule;
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
