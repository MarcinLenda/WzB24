package pl.lenda.marcin.wzb.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Promar on 22.01.2017.
 */
public class TraderException extends RuntimeException {

    private final TraderErrorCode traderErrorCode;
    private final HttpStatus httpStatus;

    private TraderException(TraderErrorCode traderErrorCode) {
        super(traderErrorCode.getDescription());
        this.traderErrorCode = traderErrorCode;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public TraderException(TraderErrorCode traderErrorCode, HttpStatus httpStatus) {
        this.traderErrorCode = traderErrorCode;
        this.httpStatus = httpStatus;
    }


    public enum  TraderErrorCode{
        TRADER_ALREADY_EXISTS("Trader already exists"),
        TRADER_NOT_FOUND("Trader not found."),
        TRADER_HAS_DOCUMENT("Trader has document"),
        NUMBER_ALREADY_EXISTS("Number already exists");

        private final String description;

        TraderErrorCode(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static TraderException traderAlreadyExists(){
        return new TraderException(TraderErrorCode.TRADER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    public static TraderException traderNotFound(){
        return new TraderException(TraderErrorCode.TRADER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public static TraderException traderNumberAlreadyExists(){
        return new TraderException(TraderErrorCode.NUMBER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }


    public TraderErrorCode getErrorCode(){
        return traderErrorCode;
    }


    public HttpStatus getStatusCode() {
        return httpStatus;
    }
}
