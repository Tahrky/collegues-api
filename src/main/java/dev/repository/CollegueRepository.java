/**
 *
 */
package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Collegue;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public interface CollegueRepository extends JpaRepository<Collegue, String> {
    Collegue findByEmail(String email);
}
