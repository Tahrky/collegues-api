/**
 * 
 */
package dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Utilisateur;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByCollegueEmail(String email);
}