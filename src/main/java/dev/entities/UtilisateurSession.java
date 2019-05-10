/**
 *
 */
package dev.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Entity
public class UtilisateurSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String motDePasse;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="matricule")
    Collegue collegue;


    public UtilisateurSession() {
    }

    public UtilisateurSession(Collegue collegue, String motDePasse, List<String> roles) {
	super();
	this.motDePasse = motDePasse;
	this.roles = roles;
	this.collegue = collegue;
    }

    /**
     * Getter
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * Setter
     * @param id the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * Getter
     * @return the collegue
     */
    public Collegue getCollegue() {
	return collegue;
    }

    /**
     * Setter
     * @param collegue the collegue to set
     */
    public void setCollegue(Collegue collegue) {
	this.collegue = collegue;
    }

    public String getMotDePasse() {
	return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
    }

    public List<String> getRoles() {
	return roles;
    }

    public void setRoles(List<String> roles) {
	this.roles = roles;
    }
}
