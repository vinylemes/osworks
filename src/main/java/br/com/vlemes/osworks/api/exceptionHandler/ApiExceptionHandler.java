package br.com.vlemes.osworks.api.exceptionHandler;

import br.com.vlemes.osworks.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException exception, WebRequest request) {

        var status = HttpStatus.BAD_REQUEST;
        var exceptionMessage = new ExceptionMessage(status.value(),
                OffsetDateTime.now(),
                exception.getMessage(),
                null);
        return handleExceptionInternal(exception, exceptionMessage, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        var fieldErrorMessages = new ArrayList<FieldErrorMessage>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fieldErrorMessages.add(new FieldErrorMessage(name, message));
        }

        ExceptionMessage exceptionMessage = new ExceptionMessage(status.value(),
                OffsetDateTime.now(),
                "Um ou mais campos estão inválidos, faça o preenchimento correto e tente novamente",
                fieldErrorMessages);
        return super.handleExceptionInternal(ex, exceptionMessage, headers, status, request);
    }
}
