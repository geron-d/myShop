package by.it.academy.controllers.exceptionHandler;

import by.it.academy.dtos.responses.exceptions.ResponseError;
import by.it.academy.exceptions.product.NotEnoughProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityExistsException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

/**
 * Controller for catching throws.
 *
 * @author Maxim Zhevnov
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Catch SQLIntegrityConstraintViolationException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseError handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("such element already exists")
                .errorData(e.getMessage())
                .build();
    }

    /**
     * Catch MethodArgumentNotValidException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("argument not valid")
                .errorData(e.getMessage())
                .build();
    }

    /**
     * Catch HttpMediaTypeNotSupportedException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseError handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("media type not supported")
                .errorData(e.getMessage())
                .build();
    }

    /**
     * Catch MethodArgumentTypeMismatchException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("argument not valid")
                .errorData(e.getMessage())
                .build();
    }

    /**
     * Catch NoSuchElementException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseError handleNoSuchElementException(NoSuchElementException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("no such element")
                .errorData(e.getMessage())
                .build();
    }

    /**
     * Catch EmptyResultDataAccessException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseError handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("no such element")
                .errorData(e.getMessage())
                .build();
    }

    /**
     * Catch EntityExistsException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityExistsException.class)
    public ResponseError handleEntityExistsException(EntityExistsException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("such entity already exists")
                .errorData(e.getMessage())
                .build();
    }

    /**
     * Catch NotEnoughProductException.
     *
     * @return the ResponseError with message and errorData. Return HttpStatus.BAD_REQUEST.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughProductException.class)
    public ResponseError handleNotEnoughProductException(NotEnoughProductException e) {
        log.info(e.getMessage());
        return ResponseError.builder()
                .message("not enough product")
                .errorData(e.getMessage())
                .build();
    }

}
