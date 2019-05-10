/**
 * 
 */
package dev.entities;

import java.time.LocalDate;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueDTO
{
	static final String TIME_PATTERN = "yyyy-MM-d";

	String matricule;
	String nom;
	String prenoms;
	String email;
	String photoUrl;
	LocalDate dateDeNaissance;

	public Collegue transformerEnCollegue()
	{
		return new Collegue(matricule, nom, prenoms, email, photoUrl, dateDeNaissance);
	}

	public CollegueDTO()
	{
	}

	public CollegueDTO(String nom, String prenoms, String email, String photoUrl, LocalDate dateDeNaissance)
	{
		super();
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}

	public CollegueDTO(String matricule, String nom, String prenoms, String email, String photoUrl,
			LocalDate dateDeNaissance)
	{
		super();
		this.matricule = matricule;
		this.nom = nom;
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
	public String getMatricule()
	{
		return matricule;
	}

	/**
	 * Setter
	 * 
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule)
	{
		this.matricule = matricule;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the prenoms
	 */
	public String getPrenoms()
	{
		return prenoms;
	}

	/**
	 * Setter
	 * 
	 * @param prenoms the prenoms to set
	 */
	public void setPrenoms(String prenoms)
	{
		this.prenoms = prenoms;
	}

	/**
	 * Getter
	 * 
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Setter
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * Getter
	 * 
	 * @return the dateDeNaissance
	 */
	public LocalDate getDateDeNaissance()
	{
		return dateDeNaissance;
	}

	/**
	 * Setter
	 * 
	 * @param dateDeNaissance the dateDeNaissance to set
	 */
	public void setDateDeNaissance(LocalDate dateDeNaissance)
	{
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * Getter
	 * 
	 * @return the photoUrl
	 */
	public String getPhotoUrl()
	{
		return photoUrl;
	}

	/**
	 * Setter
	 * 
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}

}
