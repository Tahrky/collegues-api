/**
 * 
 */
package dev.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author BIRABEN-BIANCHI Hugo
 */
@ControllerAdvice
public class MyExceptionHandler
{
	@ExceptionHandler(value = CollegueNonTrouveException.class)

	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request)
	{
		String bodyOfResponse = "Collegue non trouvé";
		return ResponseEntity.status(404).body(bodyOfResponse);
	}
}
