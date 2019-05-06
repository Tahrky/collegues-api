/**
 * 
 */
package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Collegue;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public interface CollegueRepository extends JpaRepository<Collegue, String> {
    List<Collegue> findByEmail(String email);
}
