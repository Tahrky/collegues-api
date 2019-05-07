/**
 * 
 */
package dev.entities;

import java.time.LocalDate;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class NotePojo {
    private int id;
    private String message;
    private LocalDate date;

    public NotePojo() {

    }

    public NotePojo(int id, String message, LocalDate date) {
	super();
	this.id = id;
	this.message = message;
	this.date = date;
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
    public LocalDate getDate() {
	return date;
    }

    /**
     * Setter
     * 
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
	this.date = date;
    }
}
