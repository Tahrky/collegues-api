/**
 * 
 */
package dev.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.entities.Collegue;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@Component
public class StartupDataInit
{
	@Autowired
	CollegueRepository collegueRepo;
	static final String TIME_PATTERN = "yyyy-MM-d";

	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init()
	{
		LocalDate date1 = LocalDate.parse("1950-11-14", DateTimeFormatter.ofPattern(TIME_PATTERN));

		String matriculeTemp = UUID.randomUUID().toString();
		collegueRepo.save(new Collegue(matriculeTemp, "Bob", "Robert", "a@a.a", "http://photoQuiExistePas.com", date1));

		matriculeTemp = UUID.randomUUID().toString();
		collegueRepo.save(
				new Collegue(matriculeTemp, "Adeline", "Noelle", "b@b.b", "http://photoQuiE*xistePas.com", date1));

		matriculeTemp = UUID.randomUUID().toString();
		collegueRepo
				.save(new Collegue(matriculeTemp, "Bob", "Arthur", "c@c.c", "http://p*hotoQuiExistePas.com", date1));

		collegueRepo.save(new Collegue("1", "Bobbi", "Albert", "a@a.a", "http://photoQuiExi*stePas.com", date1));
	}
}
