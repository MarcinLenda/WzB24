package pl.lenda.marcin.wzb.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Promar on 09.01.2017.
 */
public class AccountException extends RuntimeException {

    private final AccountErrorCode errorCode;
    private final HttpStatus statusCode;

    private AccountException(AccountErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

    private AccountException(AccountErrorCode errorCode, HttpStatus status) {
        this.errorCode = errorCode;
        this.statusCode = status;
    }

    public enum AccountErrorCode {
        USERNAME_ALREADY_EXISTS("Username already exists"),
        NUMBER_EMPLOYEE_EXISTS("Number employee already exists"),
        WRONG_PASSWORD("WRONG_PASSWORD");

        private final String description;

        AccountErrorCode(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }



    public static AccountException usernameAlreadyExists() {
        return new AccountException(AccountErrorCode.USERNAME_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }

    public static AccountException numberEmployeeAlreadyExists() {
        return new AccountException(AccountErrorCode.NUMBER_EMPLOYEE_EXISTS, HttpStatus.CONFLICT);
    }

    public static AccountException wrongPassword(){
        return new AccountException(AccountErrorCode.WRONG_PASSWORD, HttpStatus.CONFLICT);
    }

    public AccountErrorCode getErrorCode() {
        return errorCode;
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
