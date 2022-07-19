package by.it.academy.controllers.exceptionHandler;

import by.it.academy.dtos.exceptions.ResponseError;
import by.it.academy.exceptions.product.HaveNotEnoughProductException;
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

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseError handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        log.info(e.toString());
        return new ResponseError("such element already exists", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info(e.toString());
        return new ResponseError("argument not valid", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseError handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.info(e.toString());
        return new ResponseError("media type not supported", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.info(e.toString());
        return new ResponseError("argument not valid", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ResponseError handleNumberFormatException(NumberFormatException e) {
        log.info(e.toString());
        return new ResponseError("argument not valid", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseError handleNoSuchElementException(NoSuchElementException e) {
        log.info(e.toString());
        return new ResponseError("no such element", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseError handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        log.info(e.toString());
        return new ResponseError("no such element", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityExistsException.class)
    public ResponseError handleEntityExistsException(EntityExistsException e) {
        log.info(e.toString());
        return new ResponseError("such entity already exists", e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HaveNotEnoughProductException.class)
    public ResponseError handleHaveNotEnoughProductException(HaveNotEnoughProductException e) {
        log.info(e.toString());
        return new ResponseError("haven't enough product", e.toString());
    }

}
