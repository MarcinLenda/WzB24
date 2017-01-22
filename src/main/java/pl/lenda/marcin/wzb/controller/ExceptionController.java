package pl.lenda.marcin.wzb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.lenda.marcin.wzb.dto.ErrorInfo;
import pl.lenda.marcin.wzb.exception.AccountException;
import pl.lenda.marcin.wzb.exception.ClientException;
import pl.lenda.marcin.wzb.exception.DocumentWzException;
import pl.lenda.marcin.wzb.exception.TraderException;

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(DocumentWzException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> handleExceptionDocumentWZ(DocumentWzException ex){
        logger.warn(ex.getMessage(), ex);
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ErrorInfo(ex.getErrorCode().name(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo handleBadRequest(MethodArgumentNotValidException ex) {
        logger.warn(ex.getMessage(), ex);
        return new ErrorInfo("BAD_REQUEST", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handlerInternalServerError(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorInfo("INTERNAL_SERVER_ERROR", ex.getMessage());
    }

    @ExceptionHandler(AccountException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<ErrorInfo> handleExceptionAccountUser(AccountException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ErrorInfo(ex.getErrorCode().name(), ex.getMessage()));
    }

    @ExceptionHandler(ClientException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> handleExceptionClient(ClientException ex) {
        logger.error(ex.getMessage(), ex);
       return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ErrorInfo(ex.getErrorCode().name(), ex.getMessage()));
    }

    @ExceptionHandler(TraderException.class)
    @ResponseBody
    public ResponseEntity<ErrorInfo> handleExceptionClient(TraderException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new ErrorInfo(ex.getErrorCode().name(), ex.getMessage()));
    }

}
