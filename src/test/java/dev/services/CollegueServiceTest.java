/**
 * 
 */
package dev.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import dev.entities.Collegue;
import dev.exception.CollegueInvalideException;
import dev.exception.CollegueNonTrouveException;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@RunWith(SpringRunner.class)
@SpringBootApplication
public class CollegueServiceTest
{
	@Autowired
	CollegueService collegueService;

	static String nomCorrect = "Denis";
	static String prenomCorrect = "Bob";
	static String emailCorrect = "email@a.com";
	static String photoUrlCorrect = "http://photoUrl.com";

	final static String TIME_PATTERN = "yyyy-MM-d";
	static LocalDate dateDeNaissanceCorrect = LocalDate.parse("1982-12-11", DateTimeFormatter.ofPattern(TIME_PATTERN));

	static String matricule = "1";

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_NomTropCourt()
	{
		collegueService.ajouterUnCollegue(
				new Collegue("a", prenomCorrect, emailCorrect, photoUrlCorrect, dateDeNaissanceCorrect));
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_PrenomTropCourt()
	{
		collegueService.ajouterUnCollegue(
				new Collegue(nomCorrect, "b", emailCorrect, photoUrlCorrect, dateDeNaissanceCorrect));
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_EmailTropCourt()
	{
		collegueService.ajouterUnCollegue(
				new Collegue(nomCorrect, prenomCorrect, "a@", photoUrlCorrect, dateDeNaissanceCorrect));
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_EmailSansArobase()
	{
		collegueService.ajouterUnCollegue(
				new Collegue(nomCorrect, prenomCorrect, "arthur.com", photoUrlCorrect, dateDeNaissanceCorrect));
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_photoUrlSansHttp()
	{
		collegueService.ajouterUnCollegue(
				new Collegue(nomCorrect, prenomCorrect, emailCorrect, "photo.com", dateDeNaissanceCorrect));
	}

	@Test(expected = CollegueInvalideException.class)
	public void ajouterUnCollegue_pasMajeur()
	{
		collegueService.ajouterUnCollegue(
				new Collegue(nomCorrect, prenomCorrect, emailCorrect, photoUrlCorrect, LocalDate.now()));
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierEmail_EmailTropCourt()
	{
		collegueService.modifierEmail(matricule, "aa");
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierEmail_EmailSansArobase()
	{
		collegueService.modifierEmail(matricule, "arthurLeChevalier.com");
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void modifierEmail_mauvaisMatricule()
	{
		collegueService.modifierEmail("2", emailCorrect);
	}

	@Test(expected = CollegueInvalideException.class)
	public void modifierPhotoUrl_photoUrlMauvaise()
	{
		collegueService.modifierPhotoUrl(matricule, "maPhoto.com");
	}

	@Test(expected = CollegueNonTrouveException.class)
	public void modifierPhotoUrl_mauvaisMatricule()
	{
		collegueService.modifierPhotoUrl("2", photoUrlCorrect);
	}
}
