/**
 * 
 */
package dev.entities;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class MatriculeNote {
    String matricule;
    String messageNote;

    public MatriculeNote() {

    }

    public MatriculeNote(String matricule, String messageNote) {
	super();
	this.matricule = matricule;
	this.messageNote = messageNote;
    }

    /**
     * Getter
     * 
     * @return the messageNote
     */
    public String getMessageNote() {
	return messageNote;
    }

    /**
     * Setter
     * 
     * @param messageNote the messageNote to set
     */
    public void setMessageNote(String messageNote) {
	this.messageNote = messageNote;
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
}
