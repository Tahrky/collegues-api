/**
 * 
 */
package dev.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import dev.entities.Collegue;
import dev.exception.CollegueInvalideException;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueServiceTest
{
	CollegueService collegueService = new CollegueService();
	static String nomCorrect = "Denis";
	static String prenomCorrect = "Bob";
	static String emailCorrect = "email@a.com";
	static String photoUrlCorrect = "http://photoUrl.com";
	final static String TIME_PATTERN = "yyyy-MM-d";
	static LocalDate dateDeNaissanceCorrect = LocalDate.parse("1982-12-11", DateTimeFormatter.ofPattern(TIME_PATTERN));

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
}
