/**
 * 
 */
package dev.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Collegue;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public interface CollegueRepository extends JpaRepository<Collegue, String>
{
	final String TIME_PATTERN = "yyyy-MM-d";

	default void init()
	{
		LocalDate date1 = LocalDate.parse("1950-11-14", DateTimeFormatter.ofPattern(TIME_PATTERN));

		String matriculeTemp = UUID.randomUUID().toString();
		Collegue colTemp = new Collegue(matriculeTemp, "Bob", "Robert", "a@a.a", "http://photoQuiExistePas.com", date1);
		this.save(colTemp);

		matriculeTemp = UUID.randomUUID().toString();
		this.save(new Collegue(matriculeTemp, "Adeline", "Noelle", "b@b.b", "http://photoQuiE*xistePas.com", date1));

		matriculeTemp = UUID.randomUUID().toString();
		this.save(new Collegue(matriculeTemp, "Bob", "Arthur", "c@c.c", "http://p*hotoQuiExistePas.com", date1));

		this.save(new Collegue("1", "Bobbi", "Albert", "a@a.a", "http://photoQuiExi*stePas.com", date1));
	}
}
