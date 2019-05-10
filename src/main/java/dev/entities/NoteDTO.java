/**
 *
 */
package dev.entities;

import java.time.LocalDateTime;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class NoteDTO {
    private int id;
    private String message;
    private LocalDateTime date;

    public NoteDTO() {

    }

    public NoteDTO(int id, String message, LocalDateTime date) {
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
}
