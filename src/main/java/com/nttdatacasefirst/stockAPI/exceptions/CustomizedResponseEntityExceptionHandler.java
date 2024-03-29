package com.nttdatacasefirst.stockAPI.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions (Exception ex, WebRequest request)
            throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ShareholderNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleShareholderNotFoundException(Exception ex, WebRequest request)
            throws Exception{

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ShareholderAlreadyExistException.class)
    public final ResponseEntity<ErrorDetails> handleShareholderAlreadyExistException(Exception ex, WebRequest request)
            throws Exception{

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ShareholderRegNoNullException.class)
    public final ResponseEntity<ErrorDetails> handleShareholderRegNoNullException(Exception ex, WebRequest request)
            throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CapitalIncreaseNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCapitalIncreaseNotFoundException(Exception ex, WebRequest request)
        throws Exception{

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StockNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleStockNotFoundException(Exception ex, WebRequest request)
        throws Exception{

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CapitalIncreaseIsNotEnoughException.class)
    public final ResponseEntity<ErrorDetails> handleCapitalIncreaseNotEnoughException(Exception ex, WebRequest request)
        throws Exception{

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCouponNotFoundException(Exception ex, WebRequest request)
        throws Exception{

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DividendDistributionNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleDividendDistributionNotFoundException(Exception ex, WebRequest request)
        throws Exception{

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleOperationNotFoundException(Exception ex, WebRequest request)
            throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NominalValueException.class)
    public final ResponseEntity<ErrorDetails> handleNominalValueException(Exception ex, WebRequest request)
        throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShareholderandStockNotMatchException.class)
    public final ResponseEntity<ErrorDetails> handleShareholderandStockNotMatchException(Exception ex, WebRequest request)
        throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotUpdatedException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotUpdated(Exception ex, WebRequest request)
            throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(OperationPerformedBeforeException.class)
    public final ResponseEntity<ErrorDetails> handleOperationPerformedBefore(Exception ex, WebRequest request)
            throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailAlreadyRegisteredException.class)
    public final ResponseEntity<ErrorDetails> handleMailAlreadyRegistered(Exception ex, WebRequest request)
            throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                "Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
