/**
 *
 */
package dev.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String message;
    @Column
    private LocalDateTime date;

    @ManyToOne
    private Collegue collegue;

    public Note() {

    }

    public Note(String message) {
	super();
	this.message = message;
	this.date = LocalDateTime.now();
    }

    /**
     * Getter
     *
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * Setter
     *
     * @param id the id to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * Getter
     *
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * Setter
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * Getter
     *
     * @return the date
     */
    public LocalDateTime getDate() {
	return date;
    }

    /**
     * Setter
     *
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
	this.date = date;
    }

    /**
     * Getter
     *
     * @return the collegue
     */
    public Collegue getCollegue() {
	return collegue;
    }

    /**
     * Setter
     *
     * @param collegue the collegue to set
     */
    public void setCollegue(Collegue collegue) {
	this.collegue = collegue;
    }

}
