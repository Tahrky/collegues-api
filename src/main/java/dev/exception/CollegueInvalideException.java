/**
 * 
 */
package dev.exception;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
public class CollegueInvalideException extends RuntimeException
{

	static String msg;

	/**
	 * Constructor
	 * 
	 * @param string
	 */
	public CollegueInvalideException(String message)
	{
		msg = message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3253041316308094608L;

}