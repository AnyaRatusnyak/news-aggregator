package projectnewsaggregator.exception;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String STATUS_KEY = "status";
    private static final String ERRORS_KEY = "errors";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String,Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_KEY, LocalDateTime.now());
        body.put(STATUS_KEY, HttpStatus.BAD_REQUEST);
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        body.put(ERRORS_KEY,errors);

        return new ResponseEntity<>(body,headers,status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        Map<String, Object> body = createErrorBody(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistrationException.class)
    protected ResponseEntity<Object> handleRegistrationException(
            RegistrationException ex) {
        Map<String, Object> body = createErrorBody(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createErrorBody(HttpStatus status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP_KEY, LocalDateTime.now());
        body.put(STATUS_KEY, status);
        body.put(ERRORS_KEY, message);
        return body;
    }

    private String getErrorMessage(ObjectError e) {
        if (e instanceof FieldError) {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            return field + " " + message;
        }
        return e.getDefaultMessage();
    }
}
