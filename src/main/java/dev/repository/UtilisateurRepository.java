/**
 *
 */
package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.UtilisateurSession;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public interface UtilisateurRepository extends JpaRepository<UtilisateurSession, Integer> {

    Optional<UtilisateurSession> findByCollegueEmail(String email);
}