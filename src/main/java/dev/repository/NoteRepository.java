/**
 *
 */
package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Note;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public interface NoteRepository extends JpaRepository<Note, Integer> {

}
