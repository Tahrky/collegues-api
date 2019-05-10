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
    protected ResponseEntity<Object> handleConflictCollegueNotFound(RuntimeException ex, WebRequest request)
    {
	String bodyOfResponse = "Collegue non trouvé";
	return ResponseEntity.status(404).body(bodyOfResponse);
    }

    @ExceptionHandler(value = CollegueInvalideException.class)
    protected ResponseEntity<Object> handleConflictCollegueInvalid(RuntimeException ex, WebRequest request)
    {
	String bodyOfResponse = "Collegue invalide : " + ex.getLocalizedMessage();
	return ResponseEntity.status(404).body(bodyOfResponse);
    }
}
