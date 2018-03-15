package twyla.exceptions;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import twyla.books.Book;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    HttpServletRequest hsr;

    static final String ERROR = "error";
    private static org.slf4j.Logger Logger;

    public RestExceptionHandler() {
        Logger = LoggerFactory.getLogger(getClass());
    }

    // 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        Logger.info(ex.getMessage());
        return handleExceptionInternal(ex, "", headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(final BadRequestException ex) {

        System.out.println("handleBadRequest");

        ex.printStackTrace();

        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
