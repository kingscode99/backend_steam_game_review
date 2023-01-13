package review.steam_game.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CheckApiException.class})
    public ResponseEntity<Object> handleApiRequestException(CheckApiException ex) {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus()).body(new RestApiException(ex.getErrorCode()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorCode errorCode = ErrorCode.NOT_VALIDATE_ARGUMENT;
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(new RestApiException(errorCode));
    }
}
